(ns pauk.core
  (:require [clojure.string :as str]))


(def 🇷🇺 (str/split "А Б В Г Д Е Ё Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я" #" "))
(def ♿️ (str/split "A B V G D E YO ZH Z I IY K L M N O P R S T U F H TS CH SH SHCH ` Y ` E YU YA" #" "))
(def 🇬🇧 (str/split "A B C D E F G H I J K L M N O P Q R S T V U W X Y Z" #" "))
(def 🕷 (str/split "А В С Д Е Г Ж Н I Ь К Л М И О Р Ц Я Ы Т Ф Ю Ш Х У П" #" "))

(defn paukize
  [text]
  (-> text
    str/upper-case
    (str/replace #"[ЁёА-я]" (zipmap 🇷🇺 ♿️))
    (str/replace #"[a-zA-Z]" (zipmap 🇬🇧 🕷))))  

(comment
  ;;testing
  (= (paukize "Hello, World!") "НЕЛЛО, ШОЯЛД!")
  (= (paukize "Здарова, кложары!") "ПДАЯОФА, КЛОПНАЯУ!"))

(defn -main
  []
  (println (paukize "Hello, World!")))

