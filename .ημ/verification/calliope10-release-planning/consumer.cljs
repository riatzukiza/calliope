(ns calliope-release-planning-receipt
 (:require [clojure.string :as str]
           [eta-mu.receipt-river.api :as api]
           [eta-mu.receipt-river.domain.receipt :as receipt]
           [eta-mu.receipt-river.shape.edn :as edn]
           ["node:fs" :as fs]
           ["node:child_process" :as child]))
(let [[mode target] *command-line-args*]
 (if (= mode "append")
  (let [now (.toISOString (js/Date.))
        payload (receipt/build-payload
          {:kind :decision :owner "root/issues"
           :origin "Calliope issue10 FT004A full release-admission planning refinement"
           :dod "Preserve all original five card criteria, issue10 eight acceptance criteria and five proof obligations, unchanged Icebox/P1/5/UUID/frontmatter/history; review law/fake-protocol scope and sizing before implementation"
           :pi "pr-sprint-planning+receipt-river" :host "independent-complete-persistent-planning-worktree"
           :manifest "docs/kanban/stories/ft-004a-define-release-and-target-laws.md docs/designs/release-admission-planning.md .ημ/verification/calliope10-release-planning receipts.edn .ημ/session-mycology/ledger.md"
           :refs "issue:octave-commons/calliope10 base:04e5ed3166941ff59b8f823d61465b9b015e8626 accepted:a731093b878f600ee30648a01f1a6cd44f3439f0 RR:154440f3c997aa9208194bba59b5edbef3654f78 personalPR1 personalPR2 personalPR3"
           :tests "Actual installed owning Rheos original/proposed configured fixtures read full Icebox/P1/5 card with unchanged input bytes after reads; original and updated proposed captures retained. COMPLETE Git fsck/connectivity, immutable source/prefix/hygiene checks and actual RR known-kind consumer; portable BB reflection. No release/backend implementation/compiler/test, native GUI/audio/media/provider/upload or shared service execution. Future red/green/fullhosted/dualhost/lint/coverage NOT RUN."
           :note "Proposed full law and fake adapter protocol refinement only. Issue10 complete delivery remains across existing FT004 owners. Historical declared2655 inaccessible only in current reachable store, not global absence or project drift; existing census61 frontier. Peer checkpoint wording gap corrected before final freeze, initial native read retained. No state transition, implementation, acceptance, closure, external write, workflow change or approval transfer."
           :decisions "Pure portable closed shapes/admission/equality/targetlaws, explicit trusted human review/rights/rubric facts, effectful persistence/provider adapters, independent attempts with durable recovery checkpoints and exact callback binding. Review identity/canonical optional normalization/receipt and journal integration/secret classification/dualhost selection/sizing. Current explicit test+coverage selectors need future inclusion/discovery control; current CI tests-only is not all issueproof. Dependencies/productsequence/sync qualification/current planning review/Ready remain holds."}
           "octave-commons/calliope" now :decision)
        event (api/build-event {:event-id (str (random-uuid)) :recorded-at now
                 :component-manifest {:eta-mu/version "1.1.1"} :command "local FT004A planning refinement"
                 :producer {:actor "root/issues"} :subject {:repo "octave-commons/calliope"}} payload)
        line (edn/format-line event) result (api/validate-line line 1)]
   (when-not (:ok result) (throw (ex-info "Actual receipt owner rejected" (dissoc result :line :event))))
   (.appendFileSync fs target (str line "\n"))
   (prn (select-keys result [:ok :source/schema :errors])))
  (let [text (if (= mode "git")
               (.execFileSync child "git" #js ["show" (str target ":receipts.edn")] #js {:encoding "utf8"})
               (.readFileSync fs target "utf8"))
        lines (str/split-lines text)
        results (mapv (fn [i line] (select-keys (api/validate-line line (inc i)) [:ok :line-number :source/schema :errors])) (range) lines)
        own (last results)]
   (prn {:total-lines (count lines) :owned-addition own
         :historical-rows (dec (count lines))
         :historical-refusals (vec (remove :ok (butlast results)))})
   (when-not (and (:ok own) (= :declared (get-in own [:source/schema :status])))
    (set! (.-exitCode js/process) 1)))))
