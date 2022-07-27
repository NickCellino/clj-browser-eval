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

(.log js/console (-> js/document
                     (.querySelectorAll "#resul")))

(.-innerHTML (aget (.querySelectorAll js/document "#result") 0))
(.-value (aget (.querySelectorAll js/document "[clj-interpreter] [clj-code]") 0))

; adding event listener to thing i queried for
(.addEventListener (aget (.querySelectorAll js/document "[clj-interpreter] [clj-code]") 0) "click" #(.log js/console "clicked!"))

(.-value (aget (.querySelectorAll js/document "#code") 0))

(comment
  (register-eval-listener)

  (replace-elem "result" "ff"))

; Find all clj-interpreters.
; attach the eval-user-input thingy to the element
(let [interpreters (.querySelectorAll js/document "[clj-interpreter]")]
  (map #(.log js/console %) interpreters)
  (map
    (fn [i]
      (.log js/console (aget (.querySelectorAll i "[clj-code]") 0)))
    interpreters))
