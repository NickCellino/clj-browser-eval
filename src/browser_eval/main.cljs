(ns browser-eval.main
  (:require [clojure.browser.repl :as repl]))

(.log js/console "whatss up world?")
(repl/connect "http://localhost:3000/repl")
(.log js/console "did repl connect thing")


(def app-state (atom {:todos 0}))

(defn add-todo
  []
  (swap! app-state
         (fn [current-state]
           (merge-with + current-state {:todos 1})))
  (.log js/console (:todos @app-state)))

(-> js/document
    (.getElementById "add-todo")
    (.addEventListener "click" (fn [] (add-todo))))


(comment
  (.log js/console #'add-todo))

