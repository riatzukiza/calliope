#!/usr/bin/env python3
"""Check captured transport references; never interpret board semantics."""
import argparse
import base64
from collections import Counter
import hashlib
import json
from pathlib import Path
import re


def references(value):
    """Discover native transport entries in a JSON manifest recursively."""
    if isinstance(value, dict):
        path = value.get("path")
        if isinstance(path, str) and path.startswith("native/") and "bytes" in value and "sha256" in value:
            yield value
        for child in value.values():
            yield from references(child)
    elif isinstance(value, list):
        for child in value:
            yield from references(child)


def record(manifest, entry):
    """Keep manifest, exact path, decoded size and hash as one identity tuple."""
    path, size, digest = entry["path"], entry["bytes"], entry["sha256"]
    if not isinstance(manifest, str) or Path(manifest).name != manifest:
        raise ValueError("invalid reference manifest name")
    if not isinstance(path, str) or not path.startswith("native/"):
        raise ValueError("invalid native reference path")
    relative = Path(path)
    if relative.is_absolute() or ".." in relative.parts:
        raise ValueError("reference leaves evidence root")
    if type(size) is not int or size < 0:
        raise ValueError("invalid decoded byte count")
    if not isinstance(digest, str) or not re.fullmatch(r"[0-9a-f]{64}", digest):
        raise ValueError("invalid decoded SHA-256")
    return manifest, path, size, digest


def verify(root):
    """Require the entire declared 48-record multiset and exact capture bytes."""
    failures = []
    expected, discovered = [], []
    paths = set()
    inventory = root / "native-reference-correction/added-artifacts.json"
    try:
        declared = json.loads(inventory.read_text())["references"]
        expected = [record(entry["manifest"], entry) for entry in declared]
        if len(expected) != 48 or len(set(expected)) != 48:
            raise ValueError("expected exactly 48 distinct declared records")
    except (OSError, ValueError, KeyError, TypeError) as error:
        failures.append({"inventory": str(inventory.relative_to(root)), "error": str(error)})
    for manifest in sorted(root.glob("*.json")):
        try:
            entries = list(references(json.loads(manifest.read_text())))
        except (OSError, ValueError) as error:
            failures.append({"manifest": manifest.name, "error": str(error)})
            continue
        for entry in entries:
            try:
                identity = record(manifest.name, entry)
                discovered.append(identity)
                relative = Path(entry["path"])
                paths.add(entry["path"])
                artifact = root / relative
                if artifact.is_symlink() or not artifact.resolve().is_relative_to(root.resolve()):
                    raise ValueError("reference resolves outside evidence root or through a symlink")
                encoded = artifact.read_bytes()
                if not encoded.endswith(b"\n") or encoded.endswith(b"\n\n"):
                    raise ValueError("expected exactly one terminal LF")
                raw = base64.b64decode(encoded[:-1], validate=True)
                if base64.b64encode(raw) + b"\n" != encoded:
                    raise ValueError("noncanonical base64")
                if len(raw) != entry["bytes"] or hashlib.sha256(raw).hexdigest() != entry["sha256"]:
                    raise ValueError("decoded size/SHA mismatch")
            except (OSError, ValueError, KeyError, TypeError) as error:
                failures.append({"manifest": manifest.name, "path": entry.get("path"), "error": str(error)})
    missing = list((Counter(expected) - Counter(discovered)).elements())
    unexpected = list((Counter(discovered) - Counter(expected)).elements())
    if missing or unexpected:
        failures.append({"error": "discovered reference multiset differs from declared inventory",
                         "missing": missing, "unexpected": unexpected})
    return {"references": len(discovered), "expected_references": len(expected),
            "unique_paths": len(paths), "failures": failures, "ok": not failures}


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--root", required=True, type=Path)
    args = parser.parse_args()
    result = verify(args.root)
    print(json.dumps(result, sort_keys=True))
    raise SystemExit(0 if result["ok"] else 1)
