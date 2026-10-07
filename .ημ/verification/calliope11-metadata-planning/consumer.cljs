(ns metadata-planning-receipt
 (:require [clojure.string :as str] [eta-mu.receipt-river.api :as api]
 [eta-mu.receipt-river.domain.receipt :as receipt] [eta-mu.receipt-river.shape.edn :as edn]
 ["node:fs" :as fs] ["node:child_process" :as child]))
(let [[mode target] *command-line-args*]
 (if (= mode "append")
  (let [now (.toISOString (js/Date.))
        payload (receipt/build-payload
          {:kind :decision :owner "root/issues" :origin "Codex isolated Calliope11 full metadata planning"
           :dod "Retain entire existing Incoming P1 five-point card, ten issue criteria and six proof obligations; pure observation identity/dedup/replay plus explicit host adapters proposed before code"
           :pi "pr-sprint-planning+receipt-river" :host "independent-complete-persistent-worktree"
           :manifest "docs/kanban/stories/re-ingest-suno-metadata-and-rebuild-its-projection-metadata.md docs/designs/suno-metadata-observation-and-replay-planning.md .ημ/verification/calliope11-metadata-planning receipts.edn .ημ/session-mycology/ledger.md"
           :refs "issue:octave-commons/calliope11 parent:04e5ed3166941ff59b8f823d61465b9b015e8626 origin:a731093b878f600ee30648a01f1a6cd44f3439f0 RR:154440f3c997aa9208194bba59b5edbef3654f78 personalPR1-4"
           :tests "Source/authority and canonical-first native PR scope inspection; actual ef3 read-task original/refined Incoming P1 points5 with readonly no-network private fixtures. Own initial direct-CLI kanban wrapper refused; corrected read-task succeeds. Exact source/card/ledger prefixes and strict transport checks; actual current receipt API and portable reflection. No implementation/backend compiler/test suite, provider/audio/rclone/LFS/source ingest or shared runtime execution."
           :note "Whole existing-card planning only. Current asset verification/mirroring is not normalized metadata observation/search replay. Distinct work/renderer/source/assets/user intent/derived fields, allowlist privacy, complete history validation, no-op and concurrent append ownership proposed. HistoricalPR7 object absent from current reachable store only; old835/825/107 remain observations not imported truth. Original Incoming and all histories preserved."
           :decisions "Review schema/equality/version ties/user-intent conflict/legacy admission/CLI ABI/serialization/whole five-point sizing, future dual-host laws and actual explicit test/coverage selectors. Sync-parent qualification, native planning/design approval, lawful Rheos Ready and all hosted/manual proofs pending. No new dependency/card/controller/acceptance promotion."}
           "octave-commons/calliope" now :decision)
        event (api/build-event {:event-id (str (random-uuid)) :recorded-at now
                 :component-manifest {:eta-mu/version "1.1.1"} :command "local whole issue11 planning refinement"
                 :producer {:actor "root/issues"} :subject {:repo "octave-commons/calliope"}} payload)
        line (edn/format-line event) result (api/validate-line line 1)]
   (when-not (:ok result) (throw (ex-info "Current owning API rejected receipt" (dissoc result :line :event))))
   (.appendFileSync fs target (str line "\n")) (prn (select-keys result [:ok :source/schema :errors])))
  (let [text (if (= mode "git") (.execFileSync child "git" #js ["show" (str target ":receipts.edn")] #js {:encoding "utf8"}) (.readFileSync fs target "utf8"))
        lines (str/split-lines text)
        results (mapv (fn [i line] (select-keys (api/validate-line line (inc i)) [:ok :line-number :source/schema :errors])) (range) lines) own (last results)]
   (prn {:total-lines (count lines) :owned-addition own :historical-rows (dec (count lines)) :historical-refusals (vec (remove :ok (butlast results)))})
   (when-not (and (:ok own) (= :declared (get-in own [:source/schema :status]))) (set! (.-exitCode js/process) 1)))))
