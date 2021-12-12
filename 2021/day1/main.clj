(ns day1.main
  (:require [clojure.string :as str]))

(def input
  (-> "/mnt/hdd/projects/advent-of-code-2021/day1/input"
      (slurp :encoding "UTF-8")
      str/split-lines
      (as-> ls (map read-string ls))))

(defn find-increment-count
  [arr]
  (first (reduce
          (fn [[acc, prev] current]
            [(if (> current prev) (+ 1 acc) acc), current])
          [0 (first arr)] (rest arr))))

(find-increment-count input)
