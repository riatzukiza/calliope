---
uuid: "calliope-issue-11-re-ingest-suno-metadata"
title: "Re-ingest Suno metadata and rebuild its projection"
status: "incoming"
type: "task"
priority: "P1"
points: "5"
labels: "recovery, ingest, projection, suno"
category: "stories"
write-id: "1788047388220-0.ocny14fyeaf13cb7tkv"
created_at: "2026-08-29T23:49:48.220Z"
---

# Re-ingest Suno metadata and rebuild its projection

GitHub issue: https://github.com/octave-commons/calliope/issues/11

## Outcome

Calliope has a current-main, append-only Suno metadata ingestion lane and a
deterministically rebuilt search/read projection. Suno remains an evidence and
media source, never ontology, review, or publication authority.

## Source evidence to preserve

Conflicted PR #7 head `194512b4ce7e9e706f555a4f933449c632bb8fb5`
contains the prior `scripts/suno_meta.clj`, 835 appended ingest events, a large
`suno-meta-v1.edn` projection, research notes, and an FT-005A board-card attempt.
Replay useful behavior through current authority; do not merge the 51 MB ledger
or copy its projection as truth.

## Scope

- Preserve distinct work/song, renderer clip, metadata observation/source hash,
  local asset, user-authored liked/tag state, and derived search fields.
- Define fixture-backed accepted input, malformed input, identity, and dedupe.
- Make repeated observations idempotent while changed source evidence appends.
- Rebuild the projection solely from canonical events and available source assets.
- Make missing/deleted sources and unavailable LFS objects explicit evidence.

## Acceptance criteria

- Re-ingesting the same source observation appends nothing.
- Changed source content or version produces a new event without rewriting history.
- Existing `ledgers/ingest.edn` bytes and order remain unchanged before appends.
- Two clean projection rebuilds have the same hash.
- Events and projections contain no credentials, secrets, local absolute paths,
  or unnecessary personal data.
- Derived search/classification fields cannot promote song identity or acceptance.
- Current `calliope` namespaces and paths are used.
- Unit and integration tests cover normalization, identity, dedupe, malformed
  metadata, epistemic status, double ingestion, and double projection rebuild.
- Governed EDN validation, `clojure -M:test`, zero-warning lint, projection drift,
  and privacy/secret checks pass in hosted CI.
- The historical 825 clips / 107 liked counts are reconciled against accessible
  sources as observations, not forced targets.
- A Receipt River entry pins sources, commands, counts, hashes, unavailable LFS
  evidence, and non-goals.

## Non-goals

Studio editing law, release/publication, provider generation, a finished-song
claim, live Suno API calls, and generated audio commits are out of scope.


## Proposed current-source refinement — issue 11

This is a planning proposal, not accepted implementation or a Rheos transition.
The original UUID, Incoming/P1/5 metadata, complete body, and event history remain
unchanged. The design is [Suno metadata observation and replay planning](../../designs/suno-metadata-observation-and-replay-planning.md).
Current source is owning main `a731093b878f600ee30648a01f1a6cd44f3439f0`;
the proposed personal stack parent is sync PR 1 at
`04e5ed3166941ff59b8f823d61465b9b015e8626`, which remains unqualified.

### Context and boundary

Current `corpus.clj tracks` preserves verified JSON asset bytes and manifest
receipts; that asset lane does not provide a normalized metadata-observation
identity, repeat-ingest deduplication, or `suno-meta-v1` search projection. Preserve
its containment, hash, manifest, external-root mirroring, and failure regressions.
FT-OPS-005 dataset transport is distinct from this semantic ingestion outcome.
FT-001A may consume these facts later but owns playable indexing and waveform
jobs; this proposal does not choose FT-000D's native read model. Issue 9 laws,
issue 10 release admission, issue 12 ADR reconciliation, and issue 13 sonic seeds
remain separate. No new hard dependencies are authored.

### Complete issue acceptance obligations

1. Commit a minimal fictional metadata fixture and reviewed contracts for accepted
   fields, malformed inputs, identities, and duplicate observations.
2. Ingesting the exact same source observation again appends no event.
3. Changed source bytes or source/schema version create a new observed event;
   prior evidence is never rewritten.
4. Existing `ledgers/ingest.edn` bytes and event order remain an exact prefix;
   new writes are exactly one governed EDN map per line.
5. Rebuild `suno-meta-v1` solely from canonical events and current source assets,
   with explicit provenance and unavailable/conflicted states.
6. Two clean rebuilds from identical pinned inputs have the same output hash.
7. Missing/deleted sources and unavailable historical LFS objects remain explicit
   unavailability, never a successful empty corpus or stale successful row.
8. Events, projections, logs and committed evidence exclude credentials, secrets,
   private prompts, local absolute paths and unnecessary personal data.
9. Search/classification remains derived or provisional; it cannot establish work
   identity, merge songs, accept a concept, or accept/publish a release.
10. Use current `calliope` namespaces and paths; introduce no `fork_tales` runtime.

### Complete proof obligations

- Unit laws cover normalization, identity, deduplication, malformed metadata and
  epistemic status, with meaningful negative controls.
- Disposable fictional-corpus integration runs the actual future command twice
  for ingestion and twice for projection, including changed/missing inputs.
- Validate every proposed appended event through reviewed governed EDN contracts;
  malformed existing history is reported, not silently discarded or rewritten.
- Hosted proof includes `clojure -M:test`, zero-warning lint, projection drift/hash
  and privacy/secret scans. Add new test and coverage namespaces to the actual
  explicit selectors during implementation rather than assuming discovery.
- Reconcile historical 825 renderer clips / 107 liked observations against only
  accessible current sources, reporting coverage, drift and unavailability; these
  are historical observations, never forced target counts.
- Receipt River pins source heads, new commit SHA, commands, observed counts,
  hashes, unavailable LFS evidence and non-goals.

### Verification and risks

Review identity/schema/allowlist, legacy event handling, replay ordering, user
intent preservation and whole five-point sizing before code. Pure portable
contracts/normalization/replay decisions belong in `.cljc`; filesystem, JSON
reading, hashing, clock, append serialization and projection replacement belong
to explicit host adapters. Future proof includes concurrent identical observations,
no-op prefix preservation, changed versions, duplicate/conflicting provider IDs,
legacy corruption refusal, symlink/traversal and partial-failure isolation.
Full red/green and hosted/manual plans are in the design. If all obligations exceed
five points, reviewers must propose lawful decomposition retaining the complete
parent outcome; this proposal changes no estimate or lifecycle.

No software implementation, source corpus ingest, provider/API request, audio,
rclone, LFS retrieval or native player execution is performed by this planning
change. Planning review, governing design approval, sync-parent qualification,
and lawful Rheos Ready admission remain held.
