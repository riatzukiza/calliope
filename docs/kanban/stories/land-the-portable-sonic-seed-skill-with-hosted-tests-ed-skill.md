---
category: "stories"
labels: "recovery, skill, sonic-seed, testing"
type: "task"
write-id: "1788049616176-0.sm2vjugv2vasr8ymruu"
points: "5"
title: "Land the portable sonic-seed skill with hosted tests"
priority: "P1"
status: "incoming"
uuid: "calliope-issue-13-portable-sonic-seed-skill"
created_at: "2026-08-29T23:49:48.767Z"
---

# Land the portable sonic-seed skill with hosted tests

GitHub issue: https://github.com/octave-commons/calliope/issues/13

## Outcome

Calliope contains the portable, reviewable sonic-seed skill and deterministic
domain implementation, with boundary schemas and hosted evidence. Runtime and
binary/media distribution remain a separate follow-up.

## Source evidence to preserve

Extract the useful source from draft PR #8 head
`8fde6f899f31bc222285bc0ca4c03f7c77200ca2` onto current main: concise skill
instructions and UI metadata, CLJC law/shape/generator, content-addressed
EDN/MIDI/WAV/receipt rendering, immutable writes, and the six-second whole-bar
minimum. Treat the old head as evidence, not merge proof.

## Scope

- One pure request-to-semantic-seed function with a stable canonical identity.
- Deterministic MIDI/WAV realization behind explicit adapters.
- Closed request, seed, and receipt contracts enforced at boundaries.
- Immutable content-addressed output and portable source-tree instructions.
- One reviewed shared adapter core or a mechanically enforced runtime parity suite.

## Acceptance criteria

- Validation rejects blank/oversized keys, non-finite numbers, duration below the
  minimum, and a documented resource-safe maximum before allocation.
- Same canonical request and generator version yields the same seed identity and
  byte-identical EDN, MIDI, WAV, and receipt artifacts.
- Semantic, rendering, or generator-version changes change the identity.
- Existing artifacts are reused only after exact hash verification; conflicts fail.
- MIDI and WAV structure, duration, format, and receipt hashes agree.
- Source-checkout runtime requirements are explicit and truthful.
- Table, unit, property, determinism, immutable-conflict, CLI-error, and
  cross-runtime golden tests run in hosted CI with zero-warning lint.
- Skill validation and a clean extracted-source smoke pass.
- A Receipt River entry pins versions, commands, assertions, hashes, and limits.

## Follow-up dependency

Runtime packaging and publication are tracked by
https://github.com/octave-commons/calliope/issues/14 and must not be folded into
this card.

## Non-goals

Bundled executables, release ZIPs, generated session media, providers, finished
songs, lyrics, product review, layout, and publication are out of scope.


## Planning refinement against accepted current main

This is a provisional refinement of this existing card, not a ready transition,
implementation, or promotion of closed-unmerged PR #8. UUID, incoming status,
points, write identity, original scope and historical clarification are preserved.
The detailed review contract is
[sonic-seed planning refinement](../../kanban-docs/sonic-seed-planning-refinement.md).

### Context and current authority

Use accepted origin main `a731093b878f600ee30648a01f1a6cd44f3439f0`,
not the historical issue baseline. The review candidate stacks on the ordinary
personal synchronization candidate; synchronization does not qualify this card.
Current `src/calliope/law/` owns Malli contracts; new portable domain and byte
encoding belong outside that directory. Accepted ADR-002 owns the external media
dataset and remains untouched. This skill emits caller-owned bounded reference
artifacts; it neither ingests the corpus nor resolves its media mount.

### Exact pure and adapter boundary

- Shared `.cljc` code owns closed request/seed/artifact-receipt contracts, bounded
  normalization, deterministic musical generation, canonical serialization, MIDI
  and WAV byte decisions and resource budgets.
- Small NBB/Babashka outer adapters supply SHA-256 over the declared canonical
  bytes, immutable local writes and CLI input/output. They must not trust a
  caller-supplied digest or duplicate music/encoding decisions.
- Propose fixed mono PCM s16le at 44,100 Hz, existing four-beat/16-step whole bars,
  an exact key limit of 256 UTF-8 bytes and finite requested duration 6–60 seconds
  at millisecond precision. Preserve key bytes without trimming or silent Unicode
  normalization. Reject unknown fields and invalid values before allocation or
  output writes. Exact versioned limits and the rounding rule need planning
  acceptance; they are not accepted music law yet.
- Canonical seed EDN determines the content address; all semantically relevant
  normalized input, rendering and generator/format versions participate. A
  changed canonical format requires a new version; no old byte-compatibility
  claim is made. The artifact receipt hashes the three other files, not itself,
  and excludes changing timestamps, absolute paths and runtime names. Receipt
  River remains the separate accountable execution ledger.

### Verification and admission

The linked note maps each required proof to future red/green commands and current
repository gates. Those files/commands are proposed; this planning diff does not
claim an application test or red result. Tests must fail on actual semantic
assertions before implementation, then pass on NBB and Babashka with byte-for-byte
parity and a deliberately failing runner proven to exit nonzero. Existing JVM
contracts/tests remain required. Runtime absence or unavailable schema execution
is a visible prerequisite, never a skipped pass.

### Estimate and proposed breakdown

Keep the actual existing 5-point task unchanged for this review. The complete
acceptance scope likely needs 8 points: proposed 3-point portable semantic
contracts/identity, 3-point deterministic bytes/immutable-write boundary and
2-point source CLI/skill/hosted proof. Planning review must either justify keeping
one bounded 5-point story or approve a formal Rheos-managed breakdown with
explicit identities and dependency links before implementation. These are
proposed slices, not new cards, hidden dependencies or a silent type/points change.

### Risks and non-goals

Cross-runtime numeric/serialization drift, unexecuted closed schemas, unbounded
allocation, partial immutable writes, omitted hosted path filters and source-only
launcher requirements are explicit risks. Issues #11/#12/#14, Suno/provider calls,
corpus events, dataset projection, native playback, release targets, packaged
runtimes and artifact publication are outside this refinement. None is added as
a hard dependency; runtime packaging remains the issue #14 follow-up.

---
Review clarification: deterministic reference MIDI/WAV generation and byte-identical golden artifacts remain required verification outputs for this skill card. The generated-session-media non-goal excludes distributing or retaining session outputs as bundled/product deliverables; it does not exclude the bounded reference artifacts required by the acceptance criteria.
---