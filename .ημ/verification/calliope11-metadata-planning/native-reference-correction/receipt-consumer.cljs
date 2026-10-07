(ns calliope-reference-correction-receipt
 (:require [clojure.string :as str] [eta-mu.receipt-river.api :as api]
 [eta-mu.receipt-river.domain.receipt :as receipt] [eta-mu.receipt-river.shape.edn :as edn]
 ["node:fs" :as fs] ["node:child_process" :as child]))
(let [[mode target] *command-line-args*]
 (if (= mode "append")
  (let [now (.toISOString (js/Date.))
        payload (receipt/build-payload
          {:kind :adjudication :owner "root/issues" :origin "Codex isolated Calliope5 review-input reference correction"
           :dod "Restore all48missingnativeartifactreferences with exact original captures; preserve ten issue criteria/six proofs/card/source/manifests/history; meaningful transport-negative checks and actual owning receipt admission"
           :pi "pr-review-settlement+receipt-river" :host "new-independent-complete-persistent-correction-worktree"
           :manifest ".ημ/verification/calliope11-metadata-planning/native .ημ/verification/calliope11-metadata-planning/native-reference-correction .ημ/verification/calliope11-metadata-planning/README.md receipts.edn .ημ/session-mycology/ledger.md"
           :refs "PR:riatzukiza/calliope5 head:d0bc0709012d87382a81fcfabb227e4b467f826d review:5442125207 inline:4206750696 thread:PRRT_kwDOU4VdRc6p5b50 item:cr-comment:v1:ccd8ea746ce1aaaa8bbe0071 RR:154440f3c997aa9208194bba59b5edbef3654f78"
           :tests "Canonical status FIRST exit0 before fullreview. All48originaldecoded size/SHA and encoded bytes exact; zero credential-pattern/query URL detections, one already published historical-card path bound to source. Reference checker48valid0, missing/altered bytes/noncanonical wrapper/emptyreferences each1, Pythoncompile0. Full tip owningAPI/new suffix and canonical portable reflection; immutable histories/source/hygiene. No compiler/package/backend/provider/audio/corpus/board execution."
           :note "Verified one native P2 finding, no separate body/nitpick/outside obligations beyond same repeated prompt. Restore referenced safe native views/exactCLI streams without fabricating raw-network losslessness. Full original source and all earlier failed attempts retained; no accepted tier or readiness uplift. Own paginated/body source-format display errors retained as operator mistakes, not upstream issue."
           :decisions "Root sole publisher and native settler after independentpeer. No approval/cohort transfer, no manualrequest while actual footerzero included/unknownreset. Correction only review input availability, not metadata implementation."}
           "octave-commons/calliope" now :adjudication)
        event (api/build-event {:event-id (str (random-uuid)) :recorded-at now
                 :component-manifest {:eta-mu/version "1.1.1"} :command "local reference correction review5442125207"
                 :producer {:actor "root/issues"} :subject {:repo "octave-commons/calliope"}} payload)
        line (edn/format-line event) result (api/validate-line line 1)]
   (when-not (:ok result) (throw (ex-info "Current owning API rejected receipt" (dissoc result :line :event))))
   (.appendFileSync fs target (str line "\n")) (prn (select-keys result [:ok :source/schema :errors])))
  (let [text (if (= mode "git") (.execFileSync child "git" #js ["show" (str target ":receipts.edn")] #js {:encoding "utf8"}) (.readFileSync fs target "utf8"))
        lines (str/split-lines text)
        results (mapv (fn [i line] (select-keys (api/validate-line line (inc i)) [:ok :line-number :source/schema :errors])) (range) lines) own (last results)]
   (prn {:total-lines (count lines) :owned-addition own :historical-rows (dec (count lines)) :declared-suffix (vec (take-last 2 results)) :historical-refusals (vec (remove :ok (butlast results)))})
   (when-not (and (:ok own) (= :declared (get-in own [:source/schema :status]))) (set! (.-exitCode js/process) 1)))))
