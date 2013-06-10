(ns blackjack.test.testmodel (:use blackjack.model)
  (:use clojure.test))


  






(deftest defualt-values 
  (reset-game)
  (is (= 3 (get-counter)))
  (is (= 1 (count(get-dealer-hand))))
   (is (= 2 (count(get-player-hand))))
   (is (=  52 (count(get-cards)))))


(deftest hit-player-values
  (reset-game)
  (is (= 3 (get-counter)))
  (is (= 1 (count(get-dealer-hand))))
   (is (= 2 (count(get-player-hand))))
   (hit-me)
   (is (= 4 (get-counter)))
   (is (= 3 (count(get-player-hand))))
   (hit-dealer)
    (is (= 5 (get-counter)))
    (is (= 2 (count(get-dealer-hand)))))




  (deftest counting-cards
  (let [KN [["a" 13] ["a" 3]] KQ [["a" 13] ["a" 12]] A [["a" 1]] AA [["a" 1] ["a" 1]  ] AAA [["a" 1] ["a" 1] ["a" 1] ] AAAA [["a" 1] ["a" 1] ["a" 1] ["a" 1] ]   ]
    (is (= 13 (callculate-hand KN)))
    (is (= 20 (callculate-hand KQ)))
     (is (= 10 (callculate-hand A)))
      (is (= 20 (callculate-hand AA)))
       (is (= 21 (callculate-hand AAA)))
         (is (= 16 (callculate-hand (into  KN AAA))))
             (is (= 24 (callculate-hand (into  KN AA))))
             (is (= 4 (callculate-hand (into  A AAA))))))








  
  

