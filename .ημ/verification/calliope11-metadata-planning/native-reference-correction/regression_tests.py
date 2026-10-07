#!/usr/bin/env python3
"""Exercise capture reference closure in independent temporary copies."""
import argparse
import base64
import copy
import hashlib
import importlib.util
import json
import shutil
import subprocess
import sys
import tempfile
from pathlib import Path

sys.dont_write_bytecode = True


def check_canonical(root):
    """Check both manifest-relative streams without repairing recorded paths."""
    manifest = root / 'native-reference-correction/canonical-first.json'
    value = json.loads(manifest.read_text())
    missing = []
    for stream in ('stdout', 'stderr'):
        entry = value[stream]
        target = manifest.parent / entry['path']
        if not target.is_file():
            missing.append(entry['path'])
            continue
        encoded = target.read_bytes()
        assert encoded.endswith(b'\n') and not encoded.endswith(b'\n\n')
        raw = base64.b64decode(encoded[:-1], validate=True)
        assert base64.b64encode(raw) + b'\n' == encoded
        assert len(raw) == entry['bytes']
        assert hashlib.sha256(raw).hexdigest() == entry['sha256']
    return missing


def main():
    """Run real subprocess checks for omissions, tuple drift and duplicates."""
    parser = argparse.ArgumentParser()
    parser.add_argument('--root', required=True, type=Path)
    parser.add_argument('--phase', required=True, choices=('predecessor', 'corrected'))
    args = parser.parse_args()
    root = args.root.resolve()
    checker = root / 'native-reference-correction/verify-native-references.py'
    spec = importlib.util.spec_from_file_location('transport_checker', checker)
    module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(module)
    inventory = json.loads((root / 'native-reference-correction/added-artifacts.json').read_text())['references']
    records = inventory[:2]
    first_manifest = records[0]['manifest']
    results = []
    mutations = ('valid', 'missing-manifest', 'missing-reference', 'renamed-manifest',
                 'equal-count-duplicate', 'equal-count-replacement', 'changed-hash',
                 'changed-size', 'missing-artifact', 'malformed-wrapper', 'missing-inventory')
    for case in mutations:
        with tempfile.TemporaryDirectory(prefix='calliope-transport-') as temporary:
            fixture = Path(temporary) / 'evidence'
            shutil.copytree(root, fixture)
            manifest = fixture / first_manifest
            value = json.loads(manifest.read_text())
            refs = list(module.references(value))
            if case == 'missing-manifest':
                manifest.unlink()
            elif case == 'renamed-manifest':
                manifest.rename(fixture / 'renamed-reference-manifest.json')
            elif case == 'missing-reference':
                refs[0].clear()
                manifest.write_text(json.dumps(value))
            elif case in ('equal-count-duplicate', 'equal-count-replacement'):
                target = refs[1] if case == 'equal-count-duplicate' else refs[0]
                source = refs[0] if case == 'equal-count-duplicate' else refs[1]
                target.clear()
                target.update(copy.deepcopy(source))
                manifest.write_text(json.dumps(value))
            elif case in ('changed-hash', 'changed-size'):
                refs[0]['sha256' if case == 'changed-hash' else 'bytes'] = '0' * 64 if case == 'changed-hash' else records[0]['bytes'] + 1
                manifest.write_text(json.dumps(value))
            elif case == 'missing-artifact':
                (fixture / records[0]['path']).unlink()
            elif case == 'malformed-wrapper':
                artifact = fixture / records[0]['path']
                artifact.write_bytes(artifact.read_bytes() + b'\n')
            elif case == 'missing-inventory':
                (fixture / 'native-reference-correction/added-artifacts.json').unlink()
            run = subprocess.run(['python3', str(checker), '--root', str(fixture)], capture_output=True, text=True, check=False)
            expected = 0 if case == 'valid' or (args.phase == 'predecessor' and case in ('missing-manifest', 'missing-reference', 'renamed-manifest', 'equal-count-duplicate', 'equal-count-replacement', 'missing-inventory')) else 1
            results.append({'case': case, 'exit': run.returncode, 'expected': expected,
                            'stdout': run.stdout, 'stderr': run.stderr})
            assert run.returncode == expected, results[-1]
    missing = check_canonical(root)
    assert bool(missing) == (args.phase == 'predecessor'), missing
    print(json.dumps({'phase': args.phase, 'canonical_missing_paths': missing,
                      'subprocess_controls': results, 'ok': True}, sort_keys=True))


if __name__ == '__main__':
    main()
