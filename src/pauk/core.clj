(ns pauk.core
  (:require [clojure.string :as str]))


(defn s->v [& args]
  ;; ("A B C" "D" "E") -> ["A" "B" "C" "D" "E"]
  (str/split (reduce str args) #" ")) 

;; these letters stay the same in all transliteration standards
(def abvgd "A B V G D")
(def klmnoprstuf "K L M N O P R S T U F")
(def z "Z")

(def dict
  {   :rus 
          (s->v "А Б В Г Д Е Ё Ж З И Й К Л М Н О П Р С Т У Ф Х Ц Ч Ш Щ Ъ Ы Ь Э Ю Я")
      :translit 
        { :classic 
          (s->v abvgd " E YO ZH " z " I IY " klmnoprstuf " H TS CH SH SHCH ` Y ` E YU YA") 
          ;; original РАЮК subs from 2016

          :ugly
          (s->v "A B W G D YE YO ZJ Z I IJ K L M N O P R S T OO PF KH TZ CH SH SCH Y YI J JE HU EA")
          ;; 2022 overwhelmed

          :short
          (s->v abvgd " E E J " z " I I " klmnoprstuf " X C C S S ` Y ` E Y A")
          ;; 2022 short

          :iso-91995-b
          (s->v abvgd " E YO ZH " z " I J " klmnoprstuf " X CZ CH SH SHH `` Y` ` E` YU YA")
          
          :iso-r-9-1968
          (s->v abvgd " E JO ZH " z " I JJ " klmnoprstuf " KH C CH SH SHH `` Y ` EH JU JA")
          
          :mid-2113
          (s->v abvgd " E E ZH " z " I I " klmnoprstuf " KH TS CH SH SHCH IE Y ` E IU IA")
          ;; russian foreign passport transliteration standard
          
          :telegrams
          (s->v abvgd " E E J " z " I I " klmnoprstuf " H C CH SH SC ` Y ` E IU IA")

          :german
          (s->v "A B W G D JE JO SCH S I J K L M N O P R SS T U F CH Z TSCH SCH SCHTSCH J Y J E JU JA")}
      
      :eng 
        (s->v "A B C D E F G H I J K L M N O P Q R S T V U W X Y Z")
      :pauk 
        (s->v "А В С Д Е Г Ж Н I Ь К Л М И О Р Ц Я Ы Т Ф Ю Ш Х У П")})

(defn single-paukize
  [text standard]
  (-> text
    str/upper-case
    (str/replace #"[ЁёА-я]" (zipmap (-> dict :rus) (-> dict :translit standard)))
    (str/replace #"[a-zA-Z]" (zipmap (-> dict :eng) (-> dict :pauk)))))  

(defn -multi-paukize 
  ; threadingly paukizes <count> times
  [text standard count]
  (loop [x count s text]
    (when (> x -1)
       (def fin s)
       (recur (- x 1) (single-paukize s standard))))
  fin)

(defn multi-paukize
  ([text count] (-multi-paukize text :classic count))
  ([text standard count] (-multi-paukize text standard count)))

(defn paukize
  ([text standard count] (multi-paukize text standard count))
  ([text standard] (single-paukize text standard))
  ([text] (single-paukize text :classic)))

(comment
  ;;testing

  (def pangram "Съешь ещё этих мягких французских булок, да выпей же чаю") 

  (= (paukize "Hello, World!") "НЕЛЛО, ШОЯЛД!")
  (= (paukize "Здарова, кложары!") "ПДАЯОФА, КЛОПНАЯУ!")

  (paukize "That's how it works" :classic)
  
  (-> "ф"
    (paukize :classic)
    (paukize :ugly)
    (paukize :short)
    (paukize :iso-91995-b)
    (paukize :iso-r-9-1968)
    (paukize :mid-2113)
    (paukize :telegrams)
    (paukize :german))
  
  (map (fn [a b] {b (paukize a b)}) 
    (repeat 8 pangram)
    [:classic :ugly :short :iso-91995-b :iso-r-9-1968 :mid-2113 :telegrams :german]))

(defn -main
  []
  (println (paukize "Hello, World!" :classic)))




