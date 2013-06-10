(ns blackjack.routes.home
  (:use compojure.core)
  (:require [blackjack.views.layout :as layout]
            [blackjack.util :as util] [blackjack.model :as data]))




(defn home-page []
(layout/render "home.html" ))

(defn play-page []
   (data/reset-game)
  (layout/render
    "play.html" { :dealerhand (util/create-representation-hand(data/get-dealer-hand)) :playerhand (util/create-representation-hand(data/get-player-hand)) :playertotal (data/player-total) :dealertotal (data/dealer-total)}))

(defn about-page []
  (layout/render "about.html"))

(defn hitme-page []
  (data/hit-me)
  (layout/render "hitme.html" {:over21 (util/over-or-equal-21? (data/player-total)) :dealerhand (util/create-representation-hand(data/get-dealer-hand)) :playerhand (util/create-representation-hand(data/get-player-hand)) :playertotal (data/player-total) :dealertotal (data/dealer-total) }))

(defn stand-page []
  (data/stand)
  (layout/render "stand.html" {:message (util/getmessage (data/player-total) (data/dealer-total))    :dealerhand (util/create-representation-hand(data/get-dealer-hand)) :playerhand (util/create-representation-hand(data/get-player-hand)) :playertotal (data/player-total) :dealertotal (data/dealer-total)  }))

(defroutes home-routes
  (GET "/" [] (home-page))
  (POST "/play" [] (hitme-page))
   (GET "/play/stand" [] (stand-page))
  (GET "/play" [] (play-page)))
 
