(ns browser-eval.main)

(.log js/console "starting clojurescript...")
(.log js/console (. js/scittle -core))

(defn my-eval
  [s]
  (-> js/scittle
      (. -core)
      (.eval_string s)))

(defn replace-elem
  [id content]
  (-> js/document
      (.getElementById id)
      (.-innerHTML)
      (set! content)))

(defn eval-user-input
  []
  (let [user-code (-> js/document
                     (.getElementById "code")
                     (. -value))
        evaled (my-eval user-code)]
    (replace-elem "result" evaled)))

(-> js/document
    (.getElementById "eval-btn")
    (.addEventListener "click" eval-user-input))

