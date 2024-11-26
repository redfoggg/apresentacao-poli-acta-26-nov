(ns showing-repl.pesca
  (:gen-class)
  (:require
   [clj-java-decompiler.core :refer [decompile]]))

;; def e defn
(def n1 1)
(def n2 2)

(+ n1 n2)

(def da (fn [a b] (+ a b)))

(da n1 n2)

(defn db [a b]
  (+ a b))

(db n1 n2)

(decompile db)

(decompile {:a 1 :b 2})

;; Let bindings
(let [testing (- 2 1)
      b       :test]
  testing)

;; Collections
(let [lists   '(1 2 3)
      vectors [1 2 3]
      maps    {:a "a" :b 1}]
  (conj vectors 4 5 6)
  (map #(* 2 %1) vectors)
  (first vectors)
  (nth vectors 2))

;; Threading
(->> [1 2 ]
    (map #(- %1 1))
    (reduce + 0))

