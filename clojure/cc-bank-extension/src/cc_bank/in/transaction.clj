(ns cc-bank.in.transaction
  (:require
   [schema.core :as s]))

(s/defschema Transaction {(s/required-key :value)    s/Num
                          (s/required-key :category) s/Num
                          s/Keyword s/Any})

(comment (s/validate Transaction {:value 100M :category 0})
         (s/validate Transaction {:value "100"})
         (s/validate Transaction {:value :abc}))
