(ns blackjack.model :require [noir.session :as session])

(def counter 0)


(def init-state {:cards shuffeled-cards :counter counter :dealer-hand dealer-hand :player-hand player-hand})
(defn reset-game! []
  (session/put! :game-state init-state))

( swap! counter inc)
(def cardtypes  [:a :c :h :s])


(defn create-deck-of-card [ct]
  (for [t ct
        i (range 1 14)]
    [t i]))

cardtypes
(def shuffeled-cards (shuffle (create-deck-of-card cardtypes)))
(def dealer-hand [])
(def player-hand [])
(count cards)

(defn callculate-hand [crd]
  (let [ace (sort( for[y crd] (y 1)))]  
    (let[ totalace (filter #(= 1 %)ace) rt (filter #(< 1 %)ace) ] 
      (let  [countoface (count totalace) sumofKQJ (* 10(count(filter #(< 11 %) rt))) sumOfNormalCards  (apply +(filter #(> 11 %) rt)) ] 
         (+ sumOfNormalCards sumofKQJ 
            (if(zero? countoface )0 
              (+ (if(= 1 countoface) (if(> (+ sumofKQJ sumOfNormalCards 10) 21 ) 1 10) 
                    (if(= 2 countoface) (if(< (+ sumofKQJ sumOfNormalCards 11) 21) (if(= (+ sumofKQJ sumOfNormalCards) 0)20 2) 11) 
                      (if(= 3 countoface) 3  
                        (if(= 4 countoface) 4)))))))))))
 
 (defn is-valid-hand? [hand]
  (<= (callculate-hand hand) 21))
 
 
 (defn get-next-card []
     (vector (get-cards  get-counter))
 
 
(defn get-cards []
  (:cards (session/get :game-state)))

(defn get-counter []
  (:counter (session/get :game-state)))


(defn get-player-hand []
  (:player-hand (session/get :game-state)))
(defn get-dealer-hand []
  (:dealer-hand (session/get :game-state)))


(defn hit-me []
  ( into get-player-hand   (vector(get-cards get-counter))  ) )





(defn inc-counter[]
  (get-counter inc))






 (1 cards)