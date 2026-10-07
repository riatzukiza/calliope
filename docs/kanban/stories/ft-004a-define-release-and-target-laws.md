---
category: "stories"
labels: "release, law, publishing"
dependency: ["ft-000b-define-media-workbench-domain-laws", "ft-003d-preserve-render-to-release-derivation-graph"]
process: "docs/process/product-design-and-delivery.md"
phase: "4"
type: "story"
adr: "docs/adrs/adr-001-local-first-media-workbench.md"
write-id: "1788049615461-0.ydc6qsphbviw6qyvxk6"
points: "5"
title: "FT-004A: Define release manifest and publication target laws"
priority: "P1"
status: "icebox"
epic: "ft-004-prepare-releases-and-publish-through-explicit-target-capabilities"
design: "docs/designs/media-workbench-v1.md"
uuid: "ft-004a-define-release-manifest-and-publication-target-laws"
research: "docs/research/media-workbench-interface-and-publishing.md"
owner: "unassigned"
---

# FT-004A: Define release manifest and publication target laws

## Outcome

An accepted local release and each target's capability/state can be represented
without conflating export, upload, processing, manual handoff, and publication.

## Scope

- Release metadata, assets, track order, credits, lyrics, provenance, and rights
  basis.
- Local acceptance event.
- Target capability declaration.
- Publication attempt/checkpoint/outcome states.
- Credential references that contain no secrets.

## Non-goals

- Legal determination of rights ownership.
- OAuth implementation.
- Actual upload adapters.

## Acceptance criteria

- Publication request requires an accepted local release.
- Per-target states allow partial success.
- Direct, resumable, export-package, manual, and distributor capabilities remain
  distinct.
- Tokens and secrets are structurally excluded from portable records.
- Target availability/version is explicit.

## Verification

Contract and state-transition tests cover invalid premature publication, partial
target outcomes, manual handoff, retries/checkpoints, and secret-field rejection.

---
Recovery crosswalk (2026-08-30): GitHub issue #10 (https://github.com/octave-commons/calliope/issues/10) is the current-main release-admission/publication seam and proof umbrella. This card owns the pure law; FT-004F/C/D/E own assembly and target execution. Preserve the issue until its acceptance evidence is split and completed across those canonical cards.

Date correction: the preceding reconciliation comments say 2026-08-30, but Rheos recorded these operations on 2026-08-29 UTC. The substance is unchanged; this append-only correction preserves the original ledger history.

Review correction for GitHub issue #10 crosswalk: FT-004A owns release/target laws; the existing delivery set is FT-004B target-ready packages, FT-004F release assembly/acceptance, FT-004G video assets, FT-004C SoundCloud, FT-004D YouTube, FT-004E distributor/manual handoffs, and FT-004H publication activity/retry. This maps ownership only and does not assert acceptance or completion of any child.
---

## Proposed issue #10 refinement — 2026-10-07

This is a proposed planning refinement of the existing five-point law story, not
an acceptance, estimate change, lifecycle transition, or implementation. Preserve
all original scope, five acceptance criteria, dependency UUIDs, and recovery
comments above. Native Rheos still reads this card as **icebox / P1 / 5**.

### Context and outcome

[Issue #10](https://github.com/octave-commons/calliope/issues/10) requires an exact,
human-scoped local release admission followed by independent target publication.
Personal PR #3 proposes issue #9 studio/content laws; PR #2 proposes issue #13
sonic seeds. Neither supplies this release seam. This refinement is based on the
personal upstream-sync PR #1 at `04e5ed3166941ff59b8f823d61465b9b015e8626`, whose
accepted upstream ancestor is `a731093b878f600ee30648a01f1a6cd44f3439f0`. These PRs
remain separate, unmerged planning/synchronization inputs, not approvals here.

The intended law outcome is a versioned release admission referencing immutable
exports, product-review evidence, rights/credits/artwork facts, and independent
capability-bound target attempts. Export completion, local acceptance, package
readiness, upload completion, processing completion, and remote publication are
separate facts. Product review does not become translation SME or reconstruction
acceptance by reusing a receipt shape.

### Complete issue acceptance obligations

1. Refuse release admission when any referenced artifact hash/version, rights
   basis, required credit, or required product-review receipt is absent or stale.
2. Repeating a review receipt or attempt identity is idempotent **only** when its
   complete canonical payload is equal; any differing field conflicts without
   replacing the earlier durable record.
3. Target A cannot mutate, satisfy, or promote Target B, including simultaneous
   retries, checkpoints, callbacks, and outcomes for the same release.
4. Portable publication records structurally exclude credentials and refresh
   tokens. Credential references are opaque non-secret handles, not embedded
   secrets, signed URLs, or transferable bearer capabilities.
5. Pure shapes, admission decisions, equality/conflict rules, and target outcome
   transitions operate on Clojure data in `.cljc` where practical. Authentication,
   filesystem writes, clock/IDs, upload, poll, and durable append remain outer
   adapters; no domain code makes provider or ledger calls.
6. Failed, unavailable, and manual-action-required outcomes retain prior evidence
   and a durable resumable checkpoint of exact release, target, attempt and
   progress. Separately record why an external session cannot resume and which
   new attempt or manual step is lawful; never omit the recovery checkpoint or
   invent provider resumability. None asserts publication. Resume rechecks exact
   release, capability version, availability and authorization evidence.
7. Provider callbacks and polls must bind the exact target, attempt, admitted
   artifact identities/versions/hashes, and applicable checkpoint/session. Reject
   wrong-target, stale, unknown, replay-conflicting, and replaced-attempt results.
8. Product-review decision/receipt names the reviewer and rubric/version and
   remains distinct from translation SME evaluation. Any shared receipt/event
   primitive requires an explicit reviewed contract, not shared workflow ownership.

These eight obligations add precision to, and do not replace, the original five
card criteria: accepted local release, partial per-target success, all five
capability kinds, structural secret exclusion, and explicit availability/version.

### Scope and existing delivery owners

FT-004A proposes the portable release, product-review/admission, target capability,
request/attempt/checkpoint/outcome shapes and pure laws, plus a hermetic fake
adapter protocol fixture. The fixture traces an immutable export through review,
admission, and a target plan without introducing a production publishing adapter.
The proposed seam and exact test matrix are in
`docs/designs/release-admission-planning.md`.

The full issue is retained across existing cards: FT-004F owns release assembly,
explicit human acceptance and native UI; FT-004B owns target packages; FT-004G owns
video assets; FT-004C/D/E own real target effects/manual handoffs; FT-004H owns the
activity/retry view. This law plan neither implements those cards nor closes
issue #10. No new umbrella or silent reassignment is introduced. Existing
FT-000B and FT-003D dependencies stay unchanged; issues #9/#13 are body crosswalks,
not newly fabricated hard dependencies.

### Required proof and future red/green sequence

- Pure unit/property tests cover invalid/missing/stale admission evidence,
  complete-payload retry conflicts, target isolation, and closed no-secret shapes.
  The same portable fixtures must agree under JVM Clojure and a reviewed CLJS host.
- Fake target integration covers success, timeout, retry, manual handoff and
  callback mismatch, with failures preserving evidence and checkpoint semantics.
- A hermetic E2E fixture traces immutable export -> product-review receipt ->
  release admission -> target plan, with no live credentials or fake acceptance.
- Hosted `clojure -M:test`, zero-warning lint and coverage must pass. Current
  `test/calliope/test_runner.clj` and the `:cloverage` namespace list in `deps.edn`
  are explicit selectors: future implementation must add and prove discovery of
  the new tests/laws, including a deliberately failing control. Existing coverage
  does not currently measure release laws.
- Receipt River pins the immutable tested SHA, fixture hashes, exact commands,
  counts and refusals, separating hermetic evidence from any later approved live
  smoke. This planning diff records planning mechanics only.

Red: after planning qualification and lawful readiness, add portable schemas/laws
and adversarial tests against the current missing release seam; capture meaningful
failing assertions and nonzero process status. Green: implement only the reviewed
pure decisions, then independently exercise the outer fake adapter protocol.
Retain existing tests and run the complete owning gates; no filtering or waivers.

### Non-goals, risks and review decisions

No content/clip editing, arrangement UI, audio rendering, translation generation,
translation SME review, real OAuth/upload, provider credentials, new event-ledger
engine, repository workflow change, or live publication proof is part of this
planning candidate. Reusing reconstruction or ingest acceptance as release truth
is prohibited.

Review must decide the release/receipt identity and schema version contract,
complete canonical equality and optional-field normalization, human reviewer
eligibility/rubric/right facts and revocation/staleness rules, durable event and
checkpoint contract, and actual CLJS verification host. The proposed design does
not determine legal rights ownership or grant provider permission. Retrying a
local request does not guarantee exactly-once external upload.

Five points remain the original estimate. Review must determine whether this
whole law + fake protocol outcome fits five points; if not, propose an explicit
lawful breakdown covering **all** original and issue obligations before changing
estimates, creating child identities, or entering readiness. Product sequencing
(local release before publication), dependency satisfaction, governing review,
current-head PR qualification and Rheos admission remain holds.
