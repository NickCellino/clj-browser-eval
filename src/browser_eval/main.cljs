(ns browser-eval.main
  (:require
    [scittle.core :refer [eval-string]]
    [clojure.browser.dom :refer [log]]))

(.log js/console "starting clojurescript...")

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
        evaled (try
                 (eval-string user-code)
                 (catch js/Error e
                   (.log js/console "An error occurred:" e)))]
    (replace-elem "result" evaled)))

(defn register-eval-listener
  []
  (-> js/document
      (.getElementById "eval-btn")
      (.addEventListener "click" eval-user-input)))

(comment
  (register-eval-listener)

  (replace-elem "result" "ff"))
