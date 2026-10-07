#!/usr/bin/env python3
"""Check captured transport references; never interpret board semantics."""
import argparse, base64, hashlib, json
from pathlib import Path

def references(value):
    if isinstance(value, dict):
        path = value.get("path")
        if isinstance(path, str) and path.startswith("native/") and "bytes" in value and "sha256" in value:
            yield value
        for child in value.values():
            yield from references(child)
    elif isinstance(value, list):
        for child in value:
            yield from references(child)

def verify(root):
    failures = []
    count = 0
    paths = set()
    for manifest in sorted(root.glob("*.json")):
        for entry in references(json.loads(manifest.read_text())):
            count += 1
            relative = Path(entry["path"])
            paths.add(str(relative))
            artifact = root / relative
            try:
                if relative.is_absolute() or ".." in relative.parts:
                    raise ValueError("reference leaves evidence root")
                encoded = artifact.read_bytes()
                if not encoded.endswith(b"\n") or encoded.endswith(b"\n\n"):
                    raise ValueError("expected exactly one terminal LF")
                raw = base64.b64decode(encoded[:-1], validate=True)
                if base64.b64encode(raw) + b"\n" != encoded:
                    raise ValueError("noncanonical base64")
                if len(raw) != entry["bytes"] or hashlib.sha256(raw).hexdigest() != entry["sha256"]:
                    raise ValueError("decoded size/SHA mismatch")
            except (OSError, ValueError) as error:
                failures.append({"manifest": manifest.name, "path": str(relative), "error": str(error)})
    if count == 0:
        failures.append({"error": "no native references discovered"})
    return {"references": count, "unique_paths": len(paths), "failures": failures, "ok": not failures}

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--root", required=True, type=Path)
    args = parser.parse_args()
    result = verify(args.root)
    print(json.dumps(result, sort_keys=True))
    raise SystemExit(0 if result["ok"] else 1)
