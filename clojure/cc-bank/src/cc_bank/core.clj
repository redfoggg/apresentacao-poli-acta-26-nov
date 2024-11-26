(ns cc-bank.core
  (:gen-class)
  (:require
   [cc-bank.adapters.feed :as adapters.feed]
   [cc-bank.adapters.transaction :as adapters.transaction]
   [cc-bank.logic.feed :as logic.feed]
   [cheshire.core]
   [clojure.pprint :refer [pprint]]))

(def customer-id (random-uuid))

(defn new-state
  [state transactions customer-id]
  (let [feed-old-transactions (some-> state
                                      :transactions)
        new-transactions      (map adapters.transaction/in->internal! transactions)
        current-due-value     (:due-value state)
        feed                  (-> new-transactions
                                  (logic.feed/feed customer-id (or current-due-value 0M)))
        new-state             (assoc feed :transactions (concat feed-old-transactions new-transactions))]
    new-state))

(defn program-loop!
  [state]
  (let [input (read-line)]
    (if (empty? input)
      (println "Exit")
      (let [transactions (cheshire.core/parse-string input true)
            new-state    (new-state state transactions customer-id)
            feed         (adapters.feed/internal->out new-state)]
        (pprint feed)
        (program-loop! new-state)))))

(defn -main
  [& _]
  (program-loop! nil))

(comment (def mock-state {:due-value 100.00M
                          :transactions [{:value 100.00M :category :food :transaction-id (random-uuid)}
                                         {:value 200.00M :category :health :transaction-id (random-uuid)}]
                          :transaction-id (random-uuid)
                          :customer-id    (random-uuid)})
         (new-state
          mock-state
          [{:value 10M :category 0}]
          (:customer-id mock-state)))

;  [{ "value": 30.0000000000, "category": 0 }, {"value": 32.22, "category": 1}]
