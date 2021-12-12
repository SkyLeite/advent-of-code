(ns day2.main
  (:require [clojure.string :as str]))

(def input
  (-> "/mnt/hdd/projects/advent-of-code/2021/day2/input"
      (slurp :encoding "UTF-8")
      str/split-lines
      (as-> ls (map (fn [el] (str/split el #" ")) ls))))

; Part one

(defn parse-input
  [state [direction value]]
  (case direction
    "forward" (update state :x + (read-string value))
    "up" (update state :y - (read-string value))
    "down" (update state :y + (read-string value))))

(reduce * (vals (reduce parse-input {:x 0 :y 0} input)))

; Part two

(defn parse-input-two
  [state [direction value]]
  (case direction
    "forward" (-> state
                  (update :x + (read-string value))
                  (update :y + (* (get state :aim) (read-string value))))
    "up" (-> state
             (update :aim - (read-string value)))
    "down" (-> state
               (update :aim + (read-string value)))))

(reduce * (vals (select-keys (reduce parse-input-two {:x 0 :y 0 :aim 0} input) [:x :y])))
