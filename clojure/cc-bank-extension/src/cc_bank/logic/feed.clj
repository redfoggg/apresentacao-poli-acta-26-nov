(ns cc-bank.logic.feed 
  (:require
   [cc-bank.logic.transaction :as logic.transaction]
   [cc-bank.models.feed :as models.feed]
   [schema.core :as s]))


(s/defn due-value :- BigDecimal
  [transactions :- [models.feed/Transaction]
   current-due-value :- BigDecimal]
  (reduce #(+ %1 (logic.transaction/transaction-normalized-value (:value %2))) 
          current-due-value transactions))

(s/defn feed :- models.feed/Feed
  ([transactions      :- [models.feed/Transaction]
    customer-id       :- s/Uuid]
   (feed transactions customer-id 0M))
  ([transactions      :- [models.feed/Transaction]
    customer-id       :- s/Uuid
    current-due-value :- BigDecimal]
   {:due-value    (due-value transactions current-due-value)
    :customer-id  customer-id
    :transactions transactions}))

(comment (due-value [{:value 1M} {:value 2M} {:value 2M}] 0M)
         (feed [{:value 1M} {:value 2M} {:value 2M}] (random-uuid) 0M)
         (feed [{:value 1M :category :food :transaction-id (random-uuid)} 
                {:value 2M :category :none :transaction-id (random-uuid)} 
                {:value 2M :category :transportation :transaction-id (random-uuid)}
                {:value -1M :category :transportation :transaction-id (random-uuid)}] 
               (random-uuid) 0M))


