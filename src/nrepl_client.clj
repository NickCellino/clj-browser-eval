(ns nrepl-client
  (:require [cljs.repl.browser :as browser]))

(defn start-cljs-repl
  []
  (cider.piggieback/cljs-repl
    (browser/repl-env
      :static-dir ["." "out"])))

(comment
  "Evaluate this to start CLJS REPL"
  (start-cljs-repl)

  (+ 1 2)
  (.log js/console "hey")
  (.alert js/window "hey there"))

