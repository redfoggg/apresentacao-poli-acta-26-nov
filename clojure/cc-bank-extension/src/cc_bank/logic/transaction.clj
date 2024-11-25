(ns cc-bank.logic.transaction
  (:require
   [cc-bank.models.feed :as models.feed]
   [schema.core :as s]))

(s/defn transaction-formatted-value :- s/Str
  [transaction :- models.feed/Transaction]
  (let [transaction-value (:value transaction)]
    (if (neg? transaction-value)
      "Algo deu errado na sua transação"
      (str "R$ " transaction-value))))

(s/defn transaction-normalized-value :- BigDecimal
  [transaction-value :- BigDecimal]
  (if (not (neg? transaction-value))
    transaction-value
    0M))
