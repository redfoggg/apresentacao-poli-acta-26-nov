(ns cc-bank.adapters.feed
  (:require
   [cc-bank.adapters.transaction :as adapters.transaction]
   [cc-bank.models.feed :as models.feed]
   [cc-bank.out.feed :as out.feed]
   [schema.core :as s]))

(s/defn internal->out :- out.feed/Feed
  [feed :- models.feed/Feed]
  (let [due-value    (str "R$ " (:due-value feed))
        transactions (map adapters.transaction/internal->out (:transactions feed))]
    {:due-value    due-value
     :customer-id  (:customer-id feed)
     :transactions transactions}))

