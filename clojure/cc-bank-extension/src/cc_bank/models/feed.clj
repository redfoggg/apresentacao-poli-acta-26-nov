(ns cc-bank.models.feed
  (:require
   [schema.core :as s]))

(s/defschema Category (s/enum :none
                              :health
                              :transportation
                              :food))

(s/defschema Transaction {(s/required-key :value)          BigDecimal
                          (s/required-key :category)       Category
                          (s/required-key :transaction-id) s/Uuid})

(s/defschema Feed {(s/required-key :due-value)    BigDecimal
                   (s/required-key :transactions) [Transaction]
                   (s/required-key :customer-id)  s/Uuid})

(comment (s/validate Feed {:due-value 100.00M
                           :transactions [{:value 100.00M :category :food :transaction-id "fasd"}
                                          {:value 200.00M :category :health :transaction-id "dsFDS"}]
                           :transaction-id "id"}))
