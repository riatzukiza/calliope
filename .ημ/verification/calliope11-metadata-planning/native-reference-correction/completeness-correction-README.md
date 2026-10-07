# Complete native-reference inventory correction

This ordinary successor of `9a631fee3390fab97fffd882278ef543990aced4`
addresses the two verified findings in CodeRabbit review `5443363092`:

- Root `4207757287`, item `cr-comment:v1:6936b6f30d5455a06d251a7b`:
  both `canonical-first.json` stream paths now name adjacent captures without
  `audit/`. Recorded argv, exit, decoded byte counts and SHA-256 remain exact.
- Root `4207757311`, item `cr-comment:v1:e3e4d67e117b7224acee5e38`:
  the verifier compares the entire discovered multiset of manifest name, exact
  path, decoded size and SHA-256 to the existing 48 distinct records in
  `added-artifacts.json`. Missing or extra tuples and duplicate substitutions
  fail, even when the number of discovered references stays 48. Missing or
  invalid inventory, manifest JSON, byte/hash metadata and capture bytes refuse.

The inventory's `native/` paths are relative to the enclosing
`calliope11-metadata-planning` evidence root, as are the original privacy scan's
native-file identities. Command manifests beside captures use sibling paths.
`private-history-capture-reference.json` explicitly marks its old receipt-tail
capture as private provenance, not a promised published review input. Those
historical artifacts and their scope remain unchanged.

## Actual predecessor and corrected controls

`regression_tests.py` executes the actual verifier in independent temporary
copies. Against the unchanged predecessor, valid 48-record input passes, but
missing manifests/references, renamed manifests, equal-count duplicate or
replacement records, and missing inventory incorrectly also exit 0. Both
recorded `audit/canonical-first.*.b64` targets are absent. This is observed
transport-verification incompleteness, not evidence of a corpus admission bug.

The corrected positive input exits 0 with 48 records and 48 unique paths.
All ten hostile cases exit 1: missing manifest, missing reference, renamed
manifest, equal-count duplicate, equal-count replacement, changed SHA, changed
size, missing artifact, malformed wrapper and missing inventory. Both corrected
canonical-first sibling captures preserve the original recorded size and SHA.
The existing native wrappers have exactly one terminal LF, including the
one-byte wrapper for an empty stream. No wrapper is normalized or regenerated.
Python syntax verification and diff hygiene also pass.

Run from the repository root:

```bash
python3 .ημ/verification/calliope11-metadata-planning/native-reference-correction/regression_tests.py \
  --root .ημ/verification/calliope11-metadata-planning --phase corrected
python3 .ημ/verification/calliope11-metadata-planning/native-reference-correction/verify-native-references.py \
  --root .ημ/verification/calliope11-metadata-planning
```

`completeness-command-collection.json` aggregates exact local streams and safe
native projections with argv, actual cwd, completion timestamp, exit, base64,
size and hash. Native projections with structurally withheld connector query
values are explicitly not lossless original network responses; original raw
size/hash is separate. Transient/private tool paths are execution provenance,
not a dependency or promise that the checkout contains those runtimes.
`completeness-provenance.json` binds the original helper and 48 artifact
identities, native roots and actual consumed Receipt River namespaces.

## Preservation and admission limits

The whole issue 11 card and design remain byte-identical, including all ten
original acceptance criteria, six proof obligations, Incoming/P1/5 metadata,
privacy, identity/version, no-op deduplication, replay and concurrent append
ownership. Application source, ingestion/projection ledgers, workflows, board
configuration/events, every old capture and the declared inventory stay exact.
Only the two target helpers change; receipts and canonical reflection gain
append-only suffixes. Four new evidence paths keep the whole candidate at 149
changed paths against stack base `04e5ed3166941ff59b8f823d61465b9b015e8626`,
without filters, omissions or paid review.

The actual current Receipt River API at
`154440f3c997aa9208194bba59b5edbef3654f78` validates declared physical rows
75–77. New corrective row 77 does not repair or waive the 21 historical
compatibility refusals. Full immutable-tip validation is captured outside the
candidate to avoid a self-referential commit claim. Fifteen copied owner source
files are bound by hash; the reader consumes five namespaces, with
`domain.receipt` additionally used by the writer. One canonical portable
reflection is appended in this owned checkout after discovery preflight.

No corpus/application/backend/package test, Suno/provider/API/audio/rclone/LFS,
board operation, workflow change, hosted rerun, native settlement or reviewer
request occurred. DRAFT/blocked/auto-merge-off and the unknown review-quota reset
remain. Root publication and distinct independent peer review are required;
this local correction supplies no approval, cohort, readiness or deployment.

## Incomplete extra native metadata correction

Review 5444110053 on published `3436178fe73db3b48761b26bdd8bd25d9952a81f`
identified one P1: root 4208379348, thread `PRRT_kwDOU4VdRc6p9crT`, item
`cr-comment:v1:4a0531e68b28a5e7a4f084cd`. The full body repeats this finding
and adds no separate nitpick or outside-diff obligation. The baseline accepts
all 48 intact records plus an extra `native/` path without SHA, byte count, or
both, because discovery silently omits it.

Apply the exact proposed one-line iterator change: yield every dictionary
whose string path starts with `native/`, then let `record` reject missing
metadata. Retain the entire declared 48-record multiset, canonical wrapper and
size/hash checks, and existing containment policy unchanged. The expanded real
subprocess suite passes 22 cases: three positives and 19 negatives. It retains
all ten earlier hostile controls and adds missing-SHA/bytes/both extra-reference
negatives, four inventory tuple tamper cases, external-file and external-parent
symlink negatives, and in-root-directory symlink and root-alias positives.

`incomplete-metadata-command-collection.json` is the single added evidence path.
It contains actual command argv/cwd/exits, safe canonical encoded streams and
exact raw private hashes/locations, source/API provenance and own preparation
refusals. Native views with scoped query values withheld are explicitly
projections, not lossless original native responses. Historical collections,
proofs, captures, inventory, all 48 referenced artifacts, full ten issue criteria
and six proofs, governing card/design and source/workflows/configuration/events
remain exact. Receipt and canonical portable reflection are append-only.

The whole stacked diff remains 150 paths without exclusions or paid capacity.
No application/compiler/backend/provider/audio/corpus/board/hosted test or
workflow ran. Current owning Receipt River validates the new declared row 78
while retaining 21 historical refusals. Local transport regression is not Suno
metadata implementation, Ready, approval or cohort qualification. Root alone
publishes and settles after distinct peer verification; the P1 remains open
until that native followthrough. Native included capacity is zero, allowance
one review per hour, and no explicit reset is known; no reviewer retry ran.
