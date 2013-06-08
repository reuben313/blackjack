(ns blackjack.controller
  (:use compojure.core)
  (:require [compojure.core :as compojure]
            [blackjack.view :as view]
            [blackjack.model :as model]))

(defn start-page []
  (model/reset-game!)
  (view/play-screen))



(defroutes tictactoe-routes
  (GET "/" [] (start-page))
  
