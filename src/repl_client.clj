(ns repl-client
  (:require [cljs.repl :as repl]
            [cljs.repl.browser :as browser]))

(defn repl
  [_]
  (let [env (browser/repl-env
              :port 3000
              :launch-browser false
              :static-dir ["." "out"])]
    (repl/repl
      env
      :repl-verbose true)))

