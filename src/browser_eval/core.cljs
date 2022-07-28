(ns browser-eval.core
  (:require [scittle.core :as scittle]))

(defn eval-string
  [code]
  (try
    (let [value (scittle/eval-string code)]
      {:success true :value value})
    (catch js/Error e
      {:success false :error-msg (.-message e)})))

(defn click-handler
  [code-node result-node]
  (let [result (eval-string (.-value code-node))]
    (if (:success result)
      (if result-node
        (set! (.-innerHTML result-node) (:value result))
        (.alert js/window (str "Success! Result was:\n\n" (:value result))))
      (if result-node
        (set! (.-innerHTML result-node) (str "Error: " (:error-msg result)))
        (.alert js/window (str "Oh no! There was an error evaluating your code. Message:\n\n" (:error-msg result)))))))

(defn register-clj-interpreter
  [dom-node]
  (doseq [code-eval-btn (.querySelectorAll dom-node "[clj-eval]")]
    (let [code-node (aget (.querySelectorAll dom-node "[clj-code]") 0)
          result-node (aget (.querySelectorAll dom-node "[clj-result]") 0)]
      (.addEventListener code-eval-btn "click" (partial click-handler code-node result-node)))))

(defn init
  []
  (let [interpreters (.querySelectorAll js/document "[clj-interpreter]")]
    (doseq [interp-dom-node interpreters]
      (register-clj-interpreter interp-dom-node))))

(set! (.-onload js/window) init)
