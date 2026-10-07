# Proposed release admission and independent target publication laws

Status: proposed planning refinement, not an approved replacement design or ADR.
Owner: existing FT-004A law card (icebox / P1 / five points). Refs
[Calliope #10](https://github.com/octave-commons/calliope/issues/10); this proposal
does not close the issue or claim delivery of its adapter/UI owners.

## Governing authority and source fidelity

`PROCESS.md`, accepted ADR-001 and approved `media-workbench-v1.md` govern this
proposal. The ADR makes source media immutable, release local before publication,
state independent per target and effects outer adapters. The approved design
proposes `ledgers/studio.edn` for durable creative/release decisions and
`ledgers/publication.edn` for target attempts. These are proposed product storage
boundaries, not implemented current authorities. Do not silently adopt the
reconstruction ledger's closed event vocabulary or acceptance tiers as release
admission, or create another Receipt River/Clio/Rheos implementation.

Current owning main is `a731093b878f600ee30648a01f1a6cd44f3439f0`; personal sync
parent is `04e5ed3166941ff59b8f823d61465b9b015e8626`. Foresight declares historical
Calliope pin `2655ae6eddbd20ac400a8e1ff99914c56d81b835`. The intake's Git 128 result
means that pin was unavailable in its independently copied reachable current
store. It proves neither global absence nor project drift. Census issue #61 is
the existing inaccessible-history frontier; no new source-loss issue is inferred.
Old issue vocabulary references remain provenance, not a transplant authorization.

Fresh native scope shows upstream issues #9–14, no upstream open PRs, and personal
PRs #1–3. PR #1 is terminal accepted-source synchronization; PR #3 refines issue #9
studio/content laws and PR #2 refines issue #13 sonic seeds. This plan has no
approval transfer, branch adoption or declaration that those outcomes are done.

## Proposed portable shapes and law/effect boundary

Propose release, product-review decision, admission result, target capability,
publication request, attempt, checkpoint and result as versioned closed Malli
shapes with data-only laws in `.cljc` where practical. Suggested namespaces
`calliope.shape.release` and `calliope.law.release` are review proposals, not code
created here. Content work/render/clip/arrangement/export shapes remain issue #9
ownership. A canonical release version references each exact artifact identity,
version/hash and complete provenance chain; an edited artifact or release
requires new versioned evidence, never mutates prior accepted facts.

Rights basis, required credits and artwork requirements are explicit facts under
a reviewed rubric/capability contract. No shape infers legal ownership, clears
rights, fabricates artwork, or promotes a database/provider row to authority.
Required product-review evidence names its stable decision identity, exact release
and artifact versions/hashes, reviewer/authorization evidence, rubric identity and
version, reviewed scope and explicit decision. Missing/stale/revoked evidence
refuses admission with data reasons. A complete manifest is not human acceptance.
Review must decide authorized reviewer evidence, revocation/freshness policy and
what exact facts are required; code may not invent them. Translation SME and
reconstruction judgment remain different domains even when a shared primitive is
explicitly reviewed.

Host adapters supply clock/identity, retrieve referenced artifacts and verify
hashes, resolve trusted reviewer/authorization evidence, append immutable events,
write packages, authenticate, upload and poll. Provider input is untrusted data;
validate its declared shape and exact binding before it reaches a pure decision.
Pure code returns event/command proposals and refusals without filesystem,
provider, credential, random or clock access. Immutable durable outcomes cannot
be replaced by cache or UI state. A rebuildable projection may display outcomes
but cannot create release acceptance.

## Complete-payload idempotency and target isolation

Review the complete canonical payload and normalization contract before choosing
an identity/hash encoding. Compare all fields of the canonical validated payload,
including optional-field presence/defaults, rubric and capability versions,
artifact hashes, target, rights/credits facts and checkpoint identities. Equality
of a field count or a subset/digest without defined canonical payload semantics
is insufficient. Same receipt/request/attempt identity + equal complete payload
returns the original result; any difference returns an inspectable conflict and
leaves the earlier event unchanged. New deliberate attempts have distinct
identities and explicit causal links; retry cannot rewrite failed history.

Separate target attempts by release version, target identity and attempt identity.
A success for target A cannot satisfy B, even when artifact hashes match. Keep
exported/package-ready, uploaded, processing and published states distinct.
Capabilities remain explicit direct, resumable, export-package, manual and
distributor forms, with version and availability evidence. A revoked or stale
capability/authorization refuses resume. Local idempotency is not exactly-once
provider behavior: uncertain upload acknowledgements require reconciliation
against the exact external session/resource and inspectable retry policy.

## Failure, callbacks and checkpoints

A failed/unavailable/manual-action-required outcome preserves prior durable
records, relevant non-secret summaries and a durable resumable checkpoint binding
the exact release, target, attempt and progress on every such outcome. Recovery
can resume from that record through a reviewed new attempt or manual step when
the provider session cannot resume; separately record the external-resume refusal
and lawful next command. Never omit the checkpoint or invent provider
resumability. A manual checklist/export is not remote
publication; absence of a live provider cannot produce a published result. No
failure or callback may erase admitted release evidence or a sibling target.

Callback/poll admission checks target, exact attempt and admitted artifact
identities/versions/hashes, capability version, expected checkpoint/external
session and result schema. Unknown/replaced attempts, wrong targets, stale or
mismatched artifacts, conflicting replays and unauthorized results fail closed.
Duplicate equal callbacks are idempotent. Out-of-order results need a reviewed
monotonic/causal rule, not timestamp guessing. Resume revalidates release evidence,
checkpoint scope, current target availability/version and authorization.
Cancellation capability belongs to the actual target operation and cannot be
inferred from a UI request; preserve existing FT-004C/D cancellation contracts.

Secrets never enter portable events, logs, public manifests or receipt captures.
Use reviewed allowlisted portable records and server-local credential resolution.
Opaque credential references must be non-secret, non-transferable handles;
provider checkpoint URLs/headers need classification so signed or credential-
bearing material stays local. Fake sentinel secrets test nested/optional fields,
unknown extensions, response summaries and error paths. Redaction does not make a
secret-bearing record acceptable; refuse it before durable append.

## Existing owner crosswalk and dependency review

FT-004A owns the pure contracts/laws and fake protocol fixture. FT-004F owns the
human acceptance application boundary and Release Builder; FT-004B packages;
FT-004G video; FT-004C SoundCloud; FT-004D YouTube; FT-004E distributor/manual
handoffs; FT-004H projection/activity/retry UI. The existing epic is coordination,
not an implementation target. Full issue #10 remains open until evidence across
these owners is accepted. No actual provider contract, UI, upload or event storage
implementation is claimed by this law planning diff.

Existing FT-004A hard dependencies FT-000B and FT-003D remain unchanged. FT-003D
remains the complete source-to-export derivation owner; issue #9/PR #3 cannot be
assumed merged or accepted. Other issue #13/#11/#12/#14 scopes are contextual,
not newly invented dependencies. Preserve the original five card acceptance
criteria as well as all eight issue criteria and five proof obligations.

## Meaningful future red/green and full proof

After actual planning qualification and lawful Rheos readiness, add tests first:

- Admission matrices missing one artifact/version/hash, stale same-ID artifact,
  missing/stale review/rubric/rights/credit and explicit human acceptance versus
  export/manifest completeness. Reject malformed/unknown fields with total data
  errors rather than accidental host exceptions.
- Equal complete retries versus each changed canonical field, optional defaults,
  duplicate receipts, independent targets and concurrent interleaved attempts.
  Preserve old records after every negative case.
- Fake adapter success, timeout/unknown acknowledgement, deliberate new attempt,
  checkpoint resume, manual/unavailable outcomes, bad callback identity, stale
  capability/authorization and duplicate/out-of-order results. Fake adapters have
  no network/credential access and cannot claim real publication.
- A single hermetic E2E export -> product-review receipt -> release admission ->
  target plan trace with immutable hashes, authorized synthetic reviewer/rubric
  evidence and all prior events inspectable. Exercise negative export-is-not-
  acceptance and wrong-target controls; synthetic evidence is not live authority.
- Closed no-credential shapes and nested fake-secret canaries on all durable and
  diagnostic outputs, with public allowed fields only.

Run the same pure fixtures on JVM Clojure and a reviewed CLJS host; a proposed
future NBB runner must demonstrate it can load the actual Malli/law surface and
report deliberately failing tests as nonzero. Do not use a simplified parallel
schema or claim an untested runtime supports the dependencies. Runtime/tool pin
and exact CLJS command are planning decisions requiring a working cold fixture.
The owning application remains the accepted native JVM architecture.

Current full JVM command is `clojure -M:test`. `test/calliope/test_runner.clj` names
tests explicitly, so add every new test namespace and prove discovery with a
failing control. Current `clojure -M:cloverage` explicitly lists measured source
namespaces in `deps.edn`; add the new release laws and prove they appear in output
with meaningful branch/negative coverage. Preserve all existing selections/tests.
Future zero-warning lint command: `clj-kondo --lint src test scripts`, with the
existing repository configuration and zero warnings/errors. Required hosted
`clojure -M:test`, zero-warning lint and coverage must each provide exact command,
exit/counts, immutable SHA and output hashes. Current CI only declares JVM tests;
coverage/lint hosted admission wiring is a separately reviewed implementation
prerequisite, not an already active check or a planning PASS.

RR evidence distinguishes red failure, green local/hermetic proof, hosted proof,
and any later explicitly approved live-provider smoke. No live credentials,
upload, media rendering or native GUI is needed for the issue's hermetic merge
proof. Implementation of target-specific effects stays in the corresponding
existing card. Missing tooling/gates or unavailable live evidence stays visible.

## Sizing, authority and readiness decisions

The existing five-point estimate, icebox state, frontmatter, event history and
owner remain unchanged. Review must decide whether the complete law and fake
protocol scope fits five points. If not, propose explicit decomposition with all
criteria mapped before lawful metadata/state changes; no easy subset substitutes
for the full issue. Release identity/equality, schema versioning, trusted human
review/rights/rubric inputs, durable append/receipt integration, checkpoint secrecy,
CLJS verification host and gate selection remain proposed decisions.

Accepted ADR/design and local-release-first product sequencing remain governing.
The current board has no Ready cards. Dependency fulfillment, current-head native
planning review, synchronization parent qualification and lawful Rheos readiness
are required before implementation. No accepted promotion, upload permission,
issue closure or operational proof is inferred from this document.
