(ns cc-bank.adapters.transaction
  (:require
   [cc-bank.in.transaction :as in.transaction]
   [cc-bank.models.feed :as models.feed]
   [cc-bank.out.feed :as out.feed]
   [schema.core :as s]))

(defn convert-category [category]
  (cond
    (= category 0) :none
    (= category 1) :health
    (= category 2) :transportation
    (= category 3) :food))

(s/defn in->internal! :- models.feed/Transaction
  [transaction :- in.transaction/Transaction]
  {:value          (bigdec (:value transaction))
   :category       (convert-category (:category transaction))
   :transaction-id (random-uuid)})

(s/defn internal->out :- out.feed/Transaction
  [transaction :- models.feed/Transaction]
  (assoc transaction :value (str "R$ " (:value transaction))))

(comment (in->internal! {:value 30.00M :category 0})
         (internal->out {:value 30.00M :category :food :transaction-id (random-uuid)}))

