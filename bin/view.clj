(ns tictactoe.view
  (:use hiccup.form
        [hiccup.def :only [defhtml]]
        [hiccup.element :only [link-to]]
        [hiccup.page :only [html5 include-css]])
  (:require [tictactoe.model :as model]))

(defhtml layout [& content]
  (html5
   [:head
    [:title "Welcome to blackjack-luminus"]
    (include-css "/css/blackjack.css")]
   [:body [:div#wrapper content]]))


(defn play-screen []
  (layout
    [:div 
     [:p "Player , it is your turn!"]]))

