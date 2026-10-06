# Provisional sonic-seed planning refinement

Existing card: `calliope-issue-13-portable-sonic-seed-skill` ([issue13](https://github.com/octave-commons/calliope/issues/13)). This note sharpens the existing intake, not a new umbrella plan or accepted implementation. Keep its UUID, incoming status, 5-point estimate, write-id, original scope and event history unchanged pending native planning review and lawful Rheos admission.

## Authority and source fidelity

Current accepted origin source is `a731093b878f600ee30648a01f1a6cd44f3439f0`. Personal main `efe85a036504adfb45d8cab26920a6d4482aa53d` is strictly five commits behind with no personal-only history. The planning candidate is based on separate ordinary synchronization candidate `04e5ed3166941ff59b8f823d61465b9b015e8626`; that source history is retained rather than transplanted. No synchronization or planning PR is published by this preparation.

Closed-unmerged [PR8](https://github.com/octave-commons/calliope/pull/8) at `8fde6f899f31bc222285bc0ca4c03f7c77200ca2` is observed precedent. Six small source files were inspected without execution: domain, shape, law, NBB adapter, artifact contract and runtime contract. The source has useful deterministic musical decisions and four-file artifact semantics, but its closed schema data are not executed, request resource limits are incomplete and large runtime adapters are duplicated. Source-audit assertions in issue13 remain historical, not current pass evidence. No source file is copied by this planning candidate.

Current root `PROCESS.md`, `AGENTS.md`, `docs/kanban-docs/AGENTS.md` and accepted product process apply. `src/calliope/law/` is Malli contract data, not an effects or music-algorithm directory. ADR001 governs the workbench; current accepted ADR002 governs manifest-addressed external media. The source-only sonic-seed tool requires neither dataset hydration nor native audio/device selection. Issue12's old native-runtime ADR002 is a distinct historical proposal. This work does not decide it.

## Proposed boundary and bounded defaults

| Layer | Proposed owner/path | Observable responsibility |
| --- | --- | --- |
| Closed contracts | `src/calliope/law/sonic_seed.cljc` | Request, semantic seed and artifact receipt schemas actually evaluated at each input/output boundary; unknown keys and inconsistent invariants fail. |
| Pure decisions | `src/calliope/sonic_seed/{domain,canonical,encoding}.cljc` | Shared finite-value/resource checks, normalization, musical decisions, canonical seed serialization, MIDI event/header and WAV frame/header decisions. No filesystem, Node objects, native buffers or JVM classes define semantics. |
| Effect adapters | `src/calliope/sonic_seed/infra.cljs` and `infra.clj`, explicit script entry points | Digest canonical bytes, convert shared byte decisions, verify/reuse or atomically write bounded caller-owned files, report typed CLI failure. No copied second generator or encoder. |
| Skill documentation | `skills/calliope-sonic-seed/SKILL.md`, UI metadata and two references | Truthful source-checkout commands and missing-interpreter/dependency errors. No self-contained claim, binary assets or distribution recipe. |

These are proposed target paths, not files currently present or granted implementation authority. If shared closed-contract execution cannot be supported by the declared NBB/Babashka source classpaths, record and qualify that prerequisite before implementing adapters; handwritten permissive fallback predicates are not acceptance.

Proposed first profile deliberately fixes mono 44,100-Hz PCM s16le WAV and a type-0 MIDI pattern with four beats and 16 steps per bar. No synth selection UI, codec matrix, external soundfont or provider is needed. Keep the predecessor's musical choice tables unless a separately reviewed generator version deliberately changes them.

Proposed limits: key 1–256 UTF-8 bytes and nonblank, requested minimum 6,000–60,000 integer milliseconds, supported CLI seconds converted only when exactly representable at that precision. Reject NaN, infinities, unknown keys, invalid types, too-large integers and unsupported rates/channels. Preserve accepted key bytes exactly; equivalent numeric spellings normalize to the same millisecond value. Do not silently trim or normalize Unicode keys.

Derive whole-bar duration from integer bars and BPM. Propose a documented integer/rational rounding rule for frame count, shared across hosts. Accepted requests may round up to the next whole bar; their actual duration must remain at most 64 seconds for this fixed profile. Check the derived frame budget before allocation: at most 2,822,400 frames and 5,644,844 WAV bytes including its 44-byte header. Bound semantic event counts, MIDI length and EDN/receipt serialization independently; supply exact numeric budgets and allocation assertions in the reviewed contracts before green. These constants are a proposal, not an already measured performance or schema guarantee.

## Identity and immutable artifacts

1. Validate and normalize untrusted request; reject caller-supplied hashes/seed IDs. Bind generator and canonical-format version plus every semantic/render option.
2. Produce deterministic semantic seed using shared pure decisions; enforce its closed schema and all field relationships. Canonical seed bytes determine the SHA-256 directory identity. Specify sorted key ordering, UTF-8 encoding, number representation and final newline; no generic host printer assumption.
3. Render `seed.edn`, `seed.mid` and `seed.wav` from the verified seed. Encode exact duration/frame rules once; both adapters consume those decisions.
4. Construct and enforce `receipt.edn` with seed/generator/profile identity and the three other artifact hashes. Do not make a circular receipt self-hash. Exclude wall-clock timestamps, output-root paths and runtime labels so all four files compare byte-for-byte across roots and hosts.
5. Verify all existing artifact bytes before reuse. A differing existing file, partial directory or interrupted/concurrent write must have an explicit fail-closed/recovery rule and integration proof; never overwrite immutable conflicting bytes or present a partial directory as successful output.
6. Caller chooses a fresh private output root. Artifact receipt is a product record, not a second Receipt River/Clio ledger. Separate standard Receipt River records pin execution commands, runtime versions, limits and hashes. No corpus ledger, projection, remote store or provider is mutated.

Changing canonical number/byte or rendering semantics must change the declared version; compatibility with the old PR8 seed format is unproved. Preserve its original source/hash as provenance, not an acceptance target.

## Acceptance-to-test map

| Proof | Required assertions before green |
| --- | --- |
| Closed boundaries and budgets | Unknown/missing fields, NaN/infinity, wrong scalar/map/sequence types, Unicode/blank/boundary keys, exact min/max durations, above-max rounding/frame/event/byte counts; rejected input reaches no encoder allocation or write adapter. |
| Pure identity | Many fixed keys/BPM fixtures, numeric normalization, same version/request equals same bytes/ID; changed semantic/render/version value changes address; caller digest cannot override it. |
| MIDI | Header/track lengths, bounded delta-times, tempo/event ordering, notes, end-of-track and duration agree with bars/BPM, validated by an independent test decoder. |
| WAV | RIFF/chunk sizes, signed 16-bit mono PCM, sample rate, frame count and exact rounding agree; bounded sample fixtures plus independent header decoder. |
| Immutable I/O | Two separate temporary roots; verified reuse; same-name corrupted/partial file; no overwrite on conflict; interrupted/concurrent path rule; receipts hash actual persisted bytes. |
| Runtime parity and failure propagation | NBB/Babashka compare every EDN/MIDI/WAV/receipt byte for versioned bounded golden fixtures; errors exit nonzero; intentionally failing assertion proves each runner cannot mask failure. |
| Source-only skill | Fresh source extraction with declared dependencies succeeds offline after setup; missing interpreter/dependency fails truthfully before partial output; skill metadata/validator, shell syntax and zero-warning lint pass. |
| Hosted integration | Existing JVM runner includes new law tests; new portable/parity jobs and path filters actually cover source, tests, scripts and skill. Native job/head binding is retained; local results do not substitute. |

The minimum six-second proof is a local artifact contract; it does not claim Suno upload, model invocation, playback, musical acceptance or a finished song.

## Red and green execution contract

Current main has `clojure -M:test`, a fail-propagating `calliope.test-runner`, Clojure1.12.2/Malli0.16.4 in `deps.edn` and hosted Clojure CLI1.12.2.1565/Babashka1.13.219. Current `bb.edn` declares paths only, with no portable sonic-seed test harness or dependency alias. No NBB profile is declared in current main; propose an isolated pinned NBB1.4.207 setup and explicit matching closed-contract dependencies. The observed local BB1.12.207 is different from the hosted pin and supplies no cross-version proof.

The following concrete commands name **future proposed files**, not current runnable/passing tests:

```sh
clojure -M:test
nbb -cp src:test test/calliope/sonic_seed_portable_test.cljs
bb -cp src:test test/calliope/sonic_seed_portable_test.clj
bb -cp src:test test/calliope/sonic_seed_parity_test.clj
clj-kondo --lint src/calliope/sonic_seed src/calliope/law/sonic_seed.cljc test/calliope/sonic_seed_portable_test.clj test/calliope/sonic_seed_portable_test.cljs test/calliope/sonic_seed_parity_test.clj scripts/sonic_seed.clj scripts/sonic_seed.cljs
python3 /path/to/declared/skill-creator/scripts/quick_validate.py skills/calliope-sonic-seed
```

The source test scripts must declare/import their dependency setup and aggregate failures into nonzero exits. The parity runner launches explicitly pinned interpreters with unique private temp roots; it does not install global tools or spawn services. Where a shell launcher is actually added, include `bash -n` and configured shell lint; if none is needed, record that scoped choice instead of inventing a wrapper just for a gate. Resolve the skill-validator location explicitly in setup, never assume the example absolute path exists on CI.

After lawful admission, first commit real assertion tests that fail on the rejected-input, identity, byte structure or immutable-write contract. Missing future files/namespaces alone are not meaningful red evidence. Then implement shared laws/domain/encoding, then outer adapters, keeping command/exit/assertion counts and byte hashes from red and green. Extend existing hosted workflow coverage only through a separately reviewable implementation diff; this planning diff changes no workflow or code. Required current JVM tests remain required alongside the new portable lanes.

## Estimate decision and boundaries

The actual card remains a 5-point task. My provisional estimate for its full inherited acceptance scope is **8 points**, with a proposed formal breakdown: 3pt closed semantic contracts/identity, 3pt deterministic byte realization/immutable boundary, 2pt source CLI/skill/hosted proof. Later parts depend on admitted earlier contracts. Planning reviewers must either justify retaining a bounded 5pt story with all proof or approve a Rheos-managed epic/children breakdown; do not silently change points/type, create unreviewed children or invent dependency UUIDs here. No implementation begins merely because this note is reviewable.

This is independently portable music tooling. Issues11 ingestion,12 native desktop choice,14 runtime packaging,10 release/publication and the workbench application lifecycle are outside its implementation slice. No hard dependencies on those unrelated issues are introduced. Packaging14 may consume the accepted source/parity result later, according to its existing prerequisite.

Native planning qualification and lawful Rheos ready transition remain blockers. The canonical sprint-planning instruction is “Move each reviewed story to ready” through Rheos. A historical administrative PR16 merge/approval, an independent local peer read, or this proposal cannot satisfy either gate.
