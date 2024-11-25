(ns cc-bank.out.feed 
  (:require
   [schema.core :as s]))

(s/defschema Category (s/enum :none
                              :health
                              :transportation
                              :food))

(s/defschema Transaction {(s/required-key :value)          s/Str
                          (s/required-key :category)       Category
                          (s/required-key :transaction-id) s/Uuid})

(s/defschema Feed {(s/required-key :due-value)      s/Str
                   (s/required-key :transactions)   [Transaction]
                   (s/required-key :customer-id) s/Uuid})
