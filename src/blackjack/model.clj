(ns blackjack.model (:require [noir.session :as session]))

(def dealer-hand [])

(def player-hand [])

(def counter 0)

(def cardtypes  ["d" "c" "h" "s"])

(defn create-deck-of-card [ct]
  (for [t ct
        i (range 1 14)]
    [t i]))
(def shuffeled-cards (shuffle (create-deck-of-card cardtypes)))

(def defualts {:cards (shuffle shuffeled-cards) :counter counter :dealer-hand dealer-hand :player-hand player-hand})

(def init-state (atom defualts))

(defn get-mock-data  [] @init-state) 
 
 (defn replace-value-in-map [keyvalue content]
  (reset! init-state (assoc (get-mock-data) keyvalue content)))

 
(defn get-cards []
  ( :cards (get-mock-data)))


(defn get-counter []
  (:counter (get-mock-data)))


(defn inc-counter[]
  ( inc (get-counter)))

(defn get-next-card []
     (vector((get-cards) (get-counter))))



(defn get-player-hand [](:player-hand (get-mock-data)))

(defn get-dealer-hand [] (:dealer-hand (get-mock-data)))


(defn hit-me []
    (replace-value-in-map :player-hand (into (get-player-hand)   (get-next-card))  )
    (replace-value-in-map :counter (inc (get-counter))))


(defn hit-dealer []
    (replace-value-in-map :dealer-hand (into (get-dealer-hand)   (get-next-card))  )
       (replace-value-in-map :counter (inc (get-counter))))



(defn shuffle-cards-in-deck []
  (replace-value-in-map :cards (shuffle (get-cards))))






(defn create-representation-hand [hand]
 (vec (for[h hand ]
     [ :td (str  "<image alt=\"Noir\" src=\"/img/"
       (h 0) (h 1)".gif \" />" )])))



(defn callculate-hand [crd]
  (let [ace (sort( for[y crd] (y 1)))]  
    (let[ totalace (filter #(= 1 %)ace) rt (filter #(< 1 %)ace) ] 
      (let  [countoface (count totalace) sumofKQJ (* 10(count(filter #(< 10 %) rt))) sumOfNormalCards  (apply +(filter #(> 11 %) rt)) ] 
         (+ sumOfNormalCards sumofKQJ 
            (if(zero? countoface )0 
              (+ (if(= 1 countoface) (if(> (+ sumofKQJ sumOfNormalCards 10) 21 ) 1 10) 
                    (if(= 2 countoface) (if(< (+ sumofKQJ sumOfNormalCards 11) 21) (if(= (+ sumofKQJ sumOfNormalCards) 0)20 2) 11) 
                      (if(= 3 countoface)(if(= (+ sumofKQJ sumOfNormalCards) 0)21 3) 
                        (if(= 4 countoface) 4)))))))))))
 
 (defn is-valid-hand? [hand]
  (<= (callculate-hand hand) 21))
 

(defn dealer-total []
 (callculate-hand (:dealer-hand (get-mock-data))))

(defn player-total []
 (callculate-hand (:player-hand (get-mock-data))))



(defn reset-game []
  (reset! init-state  defualts )
  (shuffle-cards-in-deck)
  (hit-me) (hit-dealer) (hit-me))

(defn get-winner[] 
  (callculate-hand (get-player-hand[])))
  
  
  (defn stand []
    (hit-dealer)
    (if(> (player-total) (dealer-total))(recur) (if (<= (dealer-total) 21 ) "dealer" "player" ) ) )
    
   
