# Studio-content law planning: full issue9 refinement

This proposal refines the body of [FT-000B](../kanban/stories/ft-000b-define-studio-domain-laws.md)
against [original issue9](https://github.com/octave-commons/calliope/issues/9).
It is a reviewable planning contract, not an accepted ADR or implementation.
The complete eleven issue invariants and six proof requirements are reproduced
in the appended card body; all remain open.

## Authority, source and admission

The accepted governing documents are [ADR-001](../adrs/adr-001-local-first-media-workbench.md),
[Media Workbench v1](media-workbench-v1.md), [PROCESS](../../PROCESS.md), and
[product delivery](../process/product-design-and-delivery.md). Historical names
and acceptance evidence remain immutable. Human-owned FT-000A currently remains
incoming; its APPROVE AFTER CHANGE record concerns the historical design and
config-discovery correction, not lifecycle admission or operator approval of
this proposal. Native read-task reports FT-000B breakdown/five points with the
same dependency. There are zero ready/todo cards in the actual37-card board.

Preparation starts from owned personal synchronization PR1 full head
`04e5ed3166941ff59b8f823d61465b9b015e8626`, retaining accepted origin main
`a731093b878f600ee30648a01f1a6cd44f3439f0` as ancestor. Personal main
`efe85a036504adfb45d8cab26920a6d4482aa53d` is not substituted for that accepted
sync base. Personal PR2 at `df4ee1e7177367521b84f35adf5f4ef09f9e862c`
already owns the separate issue13 sonic-seed refinement. Neither PR is modified.
Original closed-unmerged PR7 at `194512b4ce7e9e706f555a4f933449c632bb8fb5`
is issue9's historical source reference, not an adopted patch or current proof.
The issue's historical main43963 is retained as context, not described as current.

Current accepted source contains dataset manifest contracts in
`src/calliope/law/media.cljc` and related fixtures, but no studio-content module
or corresponding studio law tests. A media manifest is not a clip, arrangement,
export or rating contract. No passing application suite is inferred from this
static observation. All issue9 criteria remain implementation backlog.

Native planning findings must be settled on the candidate head, and Rheos must
lawfully handle the declared dependency/readiness before implementation. Five
points is preserved as the existing estimate, not a validated effort claim.
Review must assess the whole scope; if it needs re-estimation/breakdown, use a
subsequent reviewed Rheos operation. This proposal introduces no children,
UUIDs, assignments, dependency edits, status overrides or alternate board engine.

## Pure contracts and context

Follow the existing Calliope law/Malli seam, using portable Clojure data and
`.cljc` where practical. Closed schemas describe versioned objects and references;
pure contextual validators compare those values with explicitly supplied,
validated reference facts. Effects resolve content/hash/duration and accepted
reference state at outer adapters; pure validation must not read files, query a
service, inspect a device or treat a mutable projection as creative authority.
Missing, corrupt, stale or unaccepted facts fail with inspectable diagnostics.
No host-specific dependency or arbitrary evaluator moves inward.

A shape-valid render reference does not by itself prove that its bytes exist or
match a hash. The law establishes which immutable facts must agree; later
adapters must verify actual bytes through separately reviewed boundaries. An
export record similarly binds its derivation and output identities without
claiming that a declared hash proves an unexecuted render.

## Complete criterion-to-law and fixture contract

| Original invariant | Proposed law and meaningful fixtures |
| --- | --- |
| 1. Work/render/clip/arrangement/export cannot validate as one another | Versioned closed discriminated contracts; valid example of each, every cross-type substitution and unknown field negative. A work may relate to renders but cannot stand in for one. |
| 2. A playable reference cannot name a conceptual work | Closed playable vocabulary render/clip/arrangement/export; reject work and unknown kinds in queue/playlist/workspace playable positions. Work attention or rating subjects remain allowed where explicitly scoped. |
| 3. Clip names one immutable render id and content hash | One typed source reference; reject missing/wrong identity/hash, multiple sources and context mismatch. Do not resolve by title/similarity. |
| 4. Clip range ordered, nonnegative and within render duration | Validated finite numeric time representation chosen in review; boundary controls at zero and exact end, negatives, reversed/empty spans, beyond-duration and invalid numeric/context cases. No allocation or device lookup in law. |
| 5. Fade sum fits clip duration | Contextual `fade-in + fade-out <= end - start`, independent of merely natural-number fields. Exact-fit/zero controls; one-over, individually-fitting-but-sum-too-large, negative/nonfinite and changed-range cases. The law never clamps input. |
| 6. Deterministic arrangement positions and accepted clip references, preserving cross-work provenance | Explicit ordered/versioned EDL and validated accepted-reference context; reject unresolved/unaccepted clips and ambiguous positions. Repeated identical data gives equal order; any tie/position convention is explicit, not host map iteration. Allowed cross-work composition retains every source-work/render/hash identity. |
| 7. Export binds arrangement id/version, sources, encoding, renderer, output hash | Complete closed derivation/identity inputs; missing or stale fields reject. Mutate each named field independently to verify identity/conflict semantics. Review chooses canonical identity encoding/version from existing authority; no new ledger or unreviewed hash protocol. |
| 8. Ratings never promote across subject scope | Subject type/id plus dimension/scale/value/actor/time; positive bounds and scale validation, clip-to-render/work promotion negatives, independent simultaneous dimensions. No implicit aggregate acceptance. |
| 9. Marker epistemic tier explicit | Observed/derived/provisional/accepted distinctions as appropriate to approved contracts; missing/unknown tier rejects and derived/provisional annotation never becomes accepted edit. Acceptance requires explicit scoped decision facts. |
| 10. Closed non-executable smart-list vocabulary | Approved query data shapes, fields and sort vocabulary; valid composed predicates/sorts, unknown operations/fields and code-shaped/function payload negatives. No eval, provider invocation, implicit identity merge or executable callback admission. |
| 11. Unknown keys and cross-scope payloads fail closed | Closed schemas at every object/reference/query boundary, invalid nested-key fixtures and subject-type mutations. Ordered playlists, labels/dispositions and attention workspaces retain their own contracts; workspace is not a playlist or Rheos task. |

The full issue scope includes labels/dispositions, ordered playlists, saved
queries and workspaces as well as the five object types. Those are not omitted
because the strongest concrete historical gap concerns clip fades. Review must
settle identifier/version/time/hash formats, reference-context contracts,
arrangement tie/position rules, supported query operators and diagnostic
precedence before green implementation. These are proposed decisions, not
silently accepted new policy. Keep release admission10, runtime12 and packing14
separate; shared primitive shapes do not transfer their authority into issue9.

## Six original proof obligations and future red/green

1. Execute positive and negative Malli contract fixtures on the current
   implementation revision; each shape and contextual boundary above must
   actually run, including nested cases. Documentation or a schema declaration
   is not test execution.
2. Execute unit/property tests for range/fade boundaries, cross-promotion,
   deterministic arrangement order and scoped ratings. Use deterministic seeds,
   independently executed JVM and CLJS shared pure fixtures, and enough generated
   valid/invalid context to find mistakes beyond copied examples. Preserve
   failing inputs/seeds. Time/number choices must have the same declared verdict
   on both hosts.
3. Run hosted `clojure -M:test` at the exact implementation head, retaining real
   test/assertion counts, plus the explicit reviewed CLJS runner for shared
   `.cljc` laws. The current repository test runner is JVM; selecting/wiring the
   smallest CLJS test adapter is a future reviewed proof obligation, not a claim
   that a CLJS runner already exists or a package policy change here.
4. Run changed source/tests lint with zero errors and warnings; include all
   new portable fixtures and report unsupported tooling rather than skip-as-pass.
5. Audit proposed namespace/path changes for no new `fork_tales` code namespace.
   Existing historical references and unrelated legacy bytes stay intact;
   do not rename unrelated code as part of this slice.
6. Append immutable Receipt River proof bound to full implementation head,
   commands/runtimes, results/counts and limitations. Preserve every historical
   receipt/event byte. Hermetic pure fixtures do not prove live audio, native
   UI, source-media availability, export rendering or provider publication.

After lawful readiness, introduce the law/fixture contract first in red. Each
negative case must fail because the declared semantic behavior is absent or
wrong, rather than an unavailable runtime or missing namespace. Then implement
the minimum contracts/context decisions in green, with adapters outward. Verify
both invalid refusal and accepted positive controls; a validator rejecting every
object is not a fix. This planning preparation ran no JVM/CLJS application tests,
red/green fixtures, audio tools or hosted qualification jobs.

## Scope protection and evidence

The existing FT-000B prefix names releases/publication targets; it stays intact.
This plan specifies the entire issue9 content outcome and does not close or erase
remaining inherited card scope. Release admission/rights/review receipts and
per-target plans/checkpoints belong to issue10/FT-004A and its existing delivery
children. Any shared schema interface needs explicit reviewed alignment; avoid
copying those laws or claiming that content export acceptance implies release
acceptance or publication. No corpus ingest11, native runtime12, sonic skill13,
packaging14, provider, media hydration, rendering, SQLite, UI, credential or
publication effect is proposed in this preparation.

[Local preparation evidence](../../.ημ/verification/calliope-studio-law-plan-20261006/README.md)
records immutable native inputs/readbacks, current owning Receipt River API,
source/prefix preservation, hashes and actual limits. Raw captures use base64
transport when committed so decoded bytes remain exact and whitespace hygiene
can be checked without normalizing observations. Neither the evidence nor an
independent local peer pass supplies native approval, round credit, operator
acceptance or a lawful ready transition. Development publication remains in the
personal fork; final origin integration requires its separate qualified release.
