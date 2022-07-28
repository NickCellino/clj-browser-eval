(ns nrepl-client
  (:require [cljs.repl.browser :as browser]))

(defn start-cljs-repl
  "This can be used to start a CLJS REPL in local development."
  []
  (cider.piggieback/cljs-repl
    (browser/repl-env
      :static-dir ["." "out"])))

(comment
  "Evaluate this to start CLJS REPL"
  (start-cljs-repl)

  :cljs/quit

  (+ 1 2)
  (.log js/console "Hello")
  (.alert js/window "Warning, this is an alert."))

