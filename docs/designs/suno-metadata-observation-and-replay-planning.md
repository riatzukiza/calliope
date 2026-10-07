# Suno metadata observation and replay planning

Status: proposed. Existing task: `calliope-issue-11-re-ingest-suno-metadata`
(Incoming, P1, 5 points), [issue 11](https://github.com/octave-commons/calliope/issues/11).
This document proposes the entire ingestion/projection outcome; it is not a new
accepted ADR, design approval, lifecycle transition or implementation.

## Source and authority

`PROCESS.md`, accepted ADR-001/ADR-002, the approved media-workbench design and
current contracts control their stated scope. Owning main is
`a731093b878f600ee30648a01f1a6cd44f3439f0`; personal main is
`efe85a036504adfb45d8cab26920a6d4482aa53d`. Existing personal sync PR 1
`04e5ed3166941ff59b8f823d61465b9b015e8626` directly parents owning main
and preserves the five accepted upstream commits. This proposal stacks on that
metadata successor; its qualification remains prerequisite to a merge.

Current `scripts/corpus.clj tracks` uses `calliope.media.dataset` to copy/hash
assets, append `:track/discovered`, regenerate a manifest, and mirror verified
JSON into tracked `tracks/`. `calliope.law.media` governs manifests, not a
Suno-metadata observation event. Existing ingestion tests prove verified-byte
containment, symlink refusal and failed-projection completion refusal. They do
not prove metadata-observation deduplication or a `suno-meta-v1` projector.
The `.cljc` dataset contains JVM filesystem adapters behind reader conditionals;
its filename alone does not make those operations pure. Keep its current contracts
and meaningful regressions while placing new decisions in portable pure layers.

Historical issue evidence names conflicted PR 7 head
`194512b4ce7e9e706f555a4f933449c632bb8fb5`, 835 appended events, a
large projection and a prior script. That object is unavailable in this complete
reachable current store. This is scoped recovery unavailability, not proof of
global absence. Do not transplant the old script, 51 MB ledger, projection, old
FT-005A state or old count assumptions. Recover exact owning historical evidence
where available before claiming compatibility; inability to inspect it stays in
the reconciliation report. The current card and issue retain that provenance.

Suno is a metadata/media source. Provider data is untrusted input, never ontology,
work identity, human curation, accepted relationship or publication authority.
FT-OPS-005 supplies a dataset, not metadata semantics. FT-001A owns playable
indexing/waveforms and FT-000D owns native topology/read-model choice. This lane
provides portable observation/query data without choosing their adapters.
Issues 9/10/12/13 and personal plans 2/3/4 are separate; no foreign source or
review approval is adopted.

## Proposed data and pure laws

Review exact field names, event vocabulary/schema version and equality before
implementation. Suggested `calliope.law.suno-metadata` contracts and
`calliope.metadata.suno` normalization/identity/replay functions are proposals,
not an established ABI. Preserve six distinct identities: work/song; renderer
clip (the provider's identifier, not the studio playable clip); source observation
with raw-byte hash and source/schema version; local audio/artwork asset hash;
scoped user-authored liked/tag intent; derived search/classification output.
Titles, filenames, folders, embeddings and shared text cannot silently identify
or merge works. Unknown work binding stays unlinked; multiple incompatible
bindings stay conflicted. Equal provider IDs with contradictory provenance or
bytes need an explicit reviewed conflict policy, not last-write identity.

An observation carries a logical source identity, relative locator, full source
hash, version, observed tier and reviewed allowlisted metadata. Absolute host
paths remain private adapter bindings. Schema validation precedes event admission.
Deduplication compares the full reviewed observation identity and canonical
content, not just a provider ID/title, truncated hash or latest run count. Exact
repeat produces no append; changed source bytes/version produces a distinct
immutable observation even if the normalized display fields happen to match.
Ordering and ambiguous timestamp/version ties have explicit deterministic rules;
review whether canonical ledger order rather than provider timestamps controls
latest observation. No fresh clock/randomness/host path enters projection bytes.

Source-export liked/tags are observed source claims with their own provenance.
Local user decisions retain subject/actor/scope and cannot be overwritten by a
re-ingest or promoted from a provider flag. The allowlist records only necessary
fields. Private prompts, credentials, raw provider payloads, arbitrary URLs,
contact/account data and machine paths must not be copied into events/projections
or public diagnostics. Fictional canaries prove rejected/omitted fields cannot
leak. Keep raw source hashes without committing sensitive source bytes; review
which metadata is necessary rather than hiding policy in a generic sanitizer.

Pure replay consumes validated canonical observations plus explicit asset
availability facts. Search/index fields remain derived or provisional with
schema/projector/input provenance. If a referenced asset is absent, deleted,
unreadable, changed or an unavailable LFS pointer, emit/report the precise
unavailable/stale/conflicted state; do not manufacture playable bytes, drop rows
into an empty-success corpus, or continue presenting an old successful row.
Missing input is not an observation of deletion. Retain durable prior facts.

## Effect adapters and append safety

Propose an explicitly rooted local CLI adapter (for example
`bb scripts/suno_metadata.clj ingest --source-root FIXTURE --ledger FIXTURE_LEDGER`
and `project --ledger FIXTURE_LEDGER --asset-root FIXTURE --output FIXTURE_OUTPUT`);
exact flags/namespace/exit ABI must be reviewed. No command may inherit hardcoded
personal scan roots or trigger the existing corpus script's dispatch merely by
loading it. Filesystem enumeration/JSON parsing/hash/clock/append/replacement are
outer adapters; validated Clojure-shaped data crosses the pure boundary.

Read and validate all selected input and required history before emitting a
success/completion claim. Govern new events through reviewed EDN contracts,
one map per line, and preserve every old byte/order. Existing legacy record
shapes require an explicit compatibility policy; the current corpus reader's
catch-and-drop behavior is not authority to ignore corrupt history. Report line
identity/hash without disclosing sensitive payloads. The available harness lacks
the OpenCode governed-ledger plugin: implementation must provide a reviewed
contract-backed admission path or record tool unavailability, not claim it ran.
This does not authorize a second board engine or an unrelated event kernel.

Serialize read-dedup-append for the selected ledger within the intended writer
model; concurrent identical observations must not double-append. Preserve the
original prefix on malformed input, unreadable source, conflicting identity or
rejected admission. Define bounded append/write failure handling, including partial
append/recovery, without truncating existing history or claiming atomicity from
`spit :append`. Projection staging and atomic replacement operate only in the
owned output directory after complete validated replay. Failure retains the prior
projection and reports an unsuccessful attempt; a success marker cannot precede
verified output. Never follow symlink/traversal/absolute locators outside owned
roots; derived cleanup cannot delete source assets or another run's files.

## Complete outcome and proof crosswalk

All ten original issue acceptance criteria apply: (1) minimal fictional fixture
with accepted/malformed/identity/dedup contracts; (2) repeated observation adds
nothing; (3) changed content/version appends observed evidence; (4) exact existing
ingest prefix and one-map-per-line appends; (5) projection rebuilt solely from
canonical events/current assets; (6) two clean identical-input rebuild hashes;
(7) explicit deleted/missing/LFS unavailability; (8) no secret/private prompt/host
path/unnecessary personal data leakage; (9) derived/provisional search cannot
promote identity or acceptance; (10) current `calliope` namespaces, no new
`fork_tales` runtime. The original card's additional verification prose remains
an obligation, not replaced by this crosswalk.

All six original proof obligations apply: unit normalization/identity/dedup/
malformed/epistemic tests; actual-command fictional double-ingest/double-rebuild
integration; governed EDN validation of each new record; hosted tests/zero-warning
lint/projection drift+hash/privacy scan; historical 825 renderer clips/107 liked
reconciliation against accessible current evidence without forcing counts;
Receipt River with source/new SHA/commands/counts/hash/unavailable LFS/non-goals.

## Red, green and discovery

After planning review/design approval and lawful Rheos Ready admission, first
write meaningful failing laws/fixtures for the entire contract, then pure domain
functions, then effect adapters. No implementation/tests are authored here.
Pure `.cljc` contracts, identity/dedup/replay should be exercised on JVM and a
reviewed independent CLJS host; cross-host canonical ordering and hashes require
explicit fixture equivalence rather than assuming JSON map-order parity.

Future tests distinguish identical observations, changed raw bytes with unchanged
display normalization, source version changes, same title/different renderer IDs,
same renderer/conflicting work links, absent bindings, duplicate keys/malformed
JSON/EDN, history corruption, stale asset hashes, deleted files, missing roots,
LFS pointers without bytes, disallowed fields and unexpected extra fields. Use
a fictional user-curation anchor to prove repeated import cannot overwrite it.
No-op and rejected operations assert every ledger byte and sibling file unchanged.
Prove concurrent identical attempts, two independent roots, read/write failure,
partial append/replacement and symlink/traversal negative controls. A deliberately
failing async/subprocess fixture must make the official runner nonzero.

Run the actual new CLI against a small fictional corpus, ingest twice, rebuild
twice into separate clean owned directories, compare full outputs/hashes and
exact append suffix, then change and remove inputs and inspect truthful outcomes.
Do not run actual private source scanning or rclone in CI. Register every new
namespace in `test/calliope/test_runner.clj`; current discovery is explicit.
Review additions to the explicit `:cloverage` namespace list in `deps.edn` so
coverage actually measures new laws/adapters. Future full commands include
`clojure -M:test`, `clojure -M:cloverage`, and reviewed zero-warning
`clj-kondo --lint src scripts test` (not currently installed/configured as a
hosted lint step), plus deterministic projection/hash/drift and canary privacy
scans with nonzero failure controls. Actual current CI runs Clojure tests and
Rheos reads; it does not prove these new lint/projection/privacy obligations.
Record missing tools/baseline failures without a gate waiver.

Human/hosted reconciliation pins selected accessible sources/assets/manifest
identities, coverage/excluded/unavailable counts and output hashes. Old 825/107
are observations to compare with current coverage, not targets or evidence of
acceptance. Missing historical objects/LFS/assets stay explicit. Keep private
payloads out of committed reports; no live provider request/audio playback or
release publication is required by this lane. Tests provide software evidence;
explicit scoped human acceptance remains separate.

## Review decisions and holds

Review the complete five-point outcome, schema/equality/identity authority,
user-intent conflict rules, legacy admission, failure taxonomy, adapter command
ABI, append serialization, pure/host placement, discovery and hosted gates before
code. If all obligations cannot fit five points, propose a complete epic/story
breakdown through review and Rheos, retaining this parent scope and original
criteria; do not silently reduce the outcome or edit estimate/status/dependencies.
No new child cards or hard dependency UUIDs are created here. Material changes
to ADR/design authority need their own reviewed decision before implementation.

The personal sync parent remains unqualified; this new planning head needs actual
native review and required checks; governing design approval and Rheos Ready are
not inferred from native Incoming parsing, old FT-OPS Done history or a peer read.
Installed eta-mu1.1.1/Rheos0.1 and privately source-built ef3 capability remain
distinct (existing eta-mu239 distribution hold); private reads are not release
consumption or transitions. Existing publisher visibility Foresight134 remains
a known owner hold only; do not infer a new job failure before one exists.
No reviewers, quota resets, credentials, services, board operations or workflow
changes are authorized by this proposal.
