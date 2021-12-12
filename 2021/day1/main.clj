(ns day1.main
  (:require [clojure.string :as str]))

(def input
  (-> "/mnt/hdd/projects/advent-of-code/2021/day1/input"
      (slurp :encoding "UTF-8")
      str/split-lines
      (as-> ls (map read-string ls))))

; Part one

(defn find-increment-count
  [arr]
  (first (reduce
          (fn [[acc, prev] current]
            [(if (> current prev) (+ 1 acc) acc), current])
          [0 (first arr)] (rest arr))))

(defn queue-push [list element]
  (if (< (count list) 3)
    (conj list element)
    (conj (vec (drop 1 list)) element)))

; Part two

(defn find-sum-increment-count
  [arr]
  (reduce
   (fn [[acc, prev] current]
     (let [current-three-elements (queue-push prev current)
           current-sum (reduce + current-three-elements)
           previous-sum (reduce + prev)]
       [(if (and
             (= 3 (count current-three-elements))
             (= 3 (count prev))
             (> current-sum previous-sum))
          (+ 1 acc)
          acc)
        current-three-elements]))
   [0 [(first arr)]]
   (rest arr)))

(find-increment-count input)
(find-sum-increment-count input)
