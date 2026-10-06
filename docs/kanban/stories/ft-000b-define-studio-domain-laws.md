---
category: "stories"
labels: "law, malli, media-workbench"
dependency: ["ft-000a-review-and-accept-or-revise-media-workbench-authority"]
process: "docs/process/product-design-and-delivery.md"
phase: "0"
type: "story"
adr: "docs/adrs/adr-001-local-first-media-workbench.md"
write-id: "1788047453951-0.czkmcnxdlxeniwicer7"
points: "5"
title: "FT-000B: Define media workbench domain laws"
priority: "P0"
status: "breakdown"
epic: "ft-000-establish-media-workbench-authority-and-durable-studio-foundation"
design: "docs/designs/media-workbench-v1.md"
uuid: "ft-000b-define-media-workbench-domain-laws"
research: "docs/research/media-workbench-interface-and-publishing.md"
owner: "unassigned"
---

# FT-000B: Define media workbench domain laws

## Outcome

Versioned Malli `.cljc` contracts represent playable references, ratings, labels,
markers, clips, arrangements, playlists, smart lists, workspaces, exports,
releases, and publication targets without collapsing their identities.

## Scope

- Closed data contracts and registry entries.
- Scope and provenance fields.
- Rating dimensions and scales.
- Immutable render references and time ranges.
- Publication capability declarations.

## Non-goals

- Persistence adapters.
- Audio decoding or FFmpeg.
- UI components.

## Acceptance criteria

- Work, render, clip, arrangement, and export are distinct object types.
- Clip laws require immutable source identity and valid positive ranges.
- Ratings identify subject, dimension, scale, value, actor, and time.
- Playlist membership accepts declared playable refs; workspace law is distinct.
- Release and publication-attempt laws preserve per-target state.
- Invalid examples cover cross-scope promotion and malformed time ranges.

## Verification

Contract tests and negative fixtures pass under `clojure -M:test`.

---
Recovery crosswalk (2026-08-30): GitHub issue #9 (https://github.com/octave-commons/calliope/issues/9) preserves the stale PR #7 studio-law evidence and stricter contextual-validation proof for this existing canonical card. Do not merge the old tree mechanically. The issue remains open implementation backlog.

Readiness repair (2026-08-30): moved from ready back to breakdown through Rheos because declared dependency FT-000A is still incoming. Re-enter ready only after the dependency is satisfied or explicitly revised through canonical authority.

Date correction: the preceding reconciliation comments say 2026-08-30, but Rheos recorded these operations on 2026-08-29 UTC. The substance is unchanged; this append-only correction preserves the original ledger history.
---

## Planning refinement: issue9 full studio-content outcome (2026-10-06)

This is a proposal appended to the existing canonical card, not a new card,
implementation, acceptance disposition, estimate change or ready transition.
[Issue9](https://github.com/octave-commons/calliope/issues/9) retains its complete
studio-content outcome. The [planning contract](../../designs/studio-content-law-planning.md)
maps every original invariant and proof requirement to future pure laws/tests.
No criterion is discharged by this documentation.

The card remains five points in breakdown and depends on the incoming,
human-owned FT-000A. Its historical APPROVE AFTER CHANGE design disposition is
not a Rheos admission or approval of this new plan. Current-head native planning
qualification and lawful Rheos dependency/readiness decisions precede red/green
implementation. Review must decide whether five points fairly covers this full
burden; any revised estimate or breakdown must be a separate reviewed Rheos
operation, not invented children or edited metadata here.

### Complete original issue9 invariants

- [ ] Work, render, clip, arrangement, and export cannot validate as one another.
- [ ] A playable reference cannot name a conceptual work.
- [ ] A clip references exactly one immutable render id + content hash.
- [ ] Clip ranges are ordered, nonnegative, and bounded by the referenced render duration.
- [ ] Fade-in + fade-out fits the clip duration; this must be a contextual validator, not the old schema’s current “both are natural numbers” approximation.
- [ ] Arrangement positions are deterministic and references resolve to accepted clips without erasing cross-work provenance.
- [ ] Export identity binds arrangement id/version, all source hashes, encoding, renderer, and output hash.
- [ ] Ratings never promote across subject scope.
- [ ] Marker epistemic tier remains explicit; derived/provisional never becomes accepted implicitly.
- [ ] Smart-list programs use a closed, non-executable vocabulary.
- [ ] Unknown keys and cross-scope payloads fail closed.

### Complete original issue9 proof requirements

- [ ] Positive and negative Malli contract tests execute on current main.
- [ ] Unit/property tests cover range/fade boundaries, object cross-promotion, deterministic arrangement ordering, and subject-scoped ratings.
- [ ] `clojure -M:test` passes with assertion counts in hosted CI.
- [ ] Changed source/tests lint with zero errors and zero warnings.
- [ ] Namespace/path audit finds no new `fork_tales` code namespace.
- [ ] Receipt River entry pins implementation SHA, commands, results, and any unverified live behavior.

### Proposed semantic and test boundary

Use closed, versioned Malli contracts and pure `.cljc` contextual decisions for
work/render/marker/clip/arrangement/export, scoped ratings/labels/dispositions,
ordered playlists, saved closed smart-list programs and attention workspaces.
Rendering, reference lookup, hashing actual files, persistence, audio devices and
native views stay outside this planning slice. Pure validators receive validated
reference/context facts; a missing, mismatched or unaccepted reference is an
explicit failure, never a fabricated default.

Positive, negative and property fixtures must exercise independent JVM and CLJS
runners, including clip bounds and contextual fade sums, cross-type promotion,
deterministic arrangement resolution and complete export identity. Red fails for
the intended violated law; green implements the smallest pure contracts/decisions
before adapter work. Runtime parity, hosted counts, lint and namespace evidence
remain future proofs; none ran in this planning preparation.

### Existing scope and non-goals remain visible

The inherited card also names release/publication shapes. Those original bytes
remain intact. This refinement specifies the complete issue9 content outcome;
it does not complete or erase the card's remaining inherited scope. Release
admission and per-target publication semantics remain owned by issue10/FT-004A
and its existing delivery children. Review must settle any shared shape interface
without duplicating release authority or silently reducing this card's outcome.

No native runtime12, Suno ingestion11, sonic skill13, packaging14, provider call,
corpus/media hydration, audio rendering, SQLite/UI, release acceptance or
publication operation is included. No old PR7 source, historical acceptance,
ledger/event, namespace or dependency is mechanically transplanted. All original
scope, comments, frontmatter and event history are preserved.
