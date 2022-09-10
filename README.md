# 🕷 РАЮК

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.tapochqa/pauk.svg)](https://clojars.org/org.clojars.tapochqa/pauk)

Translates any latin or cyrillic text to РАЮК.


## Installation

```clojure 

;; project.clj

(defproject paukization
    :dependencies [[org.clojars.tapochqa/pauk "1.3.0"]])

;; core.clj

(ns paukization.core
    (:require [pauk.core :as pauk]))

```


## Paukization

The process is 2-step.

### 1. Transliterating cyrillic symbols to latin with chosen algorithm

For now library has 8 cyrillic transliteration algorithms:

- `:classic` original 2017 algo,
- `:ugly` more letters,
- `:short` less letters,
- `:iso-91995-b` ISO 9:1995 System B,
- `:iso-r-9-1968` ISO/R 9 (1968) Table 2,
- `:mid-2113` Russian foreign passport 2020 standard,
- `:telegrams` Russian international telegrams standard,
- `:german` Russian-German transliteration standard.

Everything non-cyrillic stays untouched.

### 2. Transliterating latin symbols back to РАЮК cyrillic

```
A B C D E F G H I J K L M N O P Q R S T V U W X Y Z

↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓ ↓

А В С Д Е Г Ж Н I Ь К Л М И О Р Ц Я Ы Т Ф Ю Ш Х У П
```

### Calling functions

```clojure
(paukize s)                 ;; Paukizes string with :classic 
(paukize s algo)            ;; Paukizes with selected algorithm
(paukize s algo multi)      ;; Multiple times
(multi-paukize s multi)     ;; Paukizes multiple times with :classic
```

### In action

```clojure
paukization.core=> (pauk/paukize "Hello, World!")
"НЕЛЛО, ШОЯЛД!"

paukization.core=> (pauk/paukize "Здарова, кложары!")
"ПДАЯОФА, КЛОПНАЯУ!"

paukization.core=> (pauk/paukize "Здарова, кложары!" :ugly)
"ПДАЯОША, КЛОПЬАЯУI!"

paukization.core=> (pauk/paukize "Здарова, кложары!" :ugly 10) 
"ООООООООООУЕАДАООООООООООООООУЕАОООIIА, КЛОООООООООООУЕАЬАООООООООООООООУЕАООI!"

paukization.core=> (pauk/multi-paukize "Здарова, кложары!" 10) 
"УЮЮУЮЮУЮУЮЮУЮАДАУЮЮУЮЮУЮУЮЮУЮЮУЮУЮЮУЮУЮЮУЮЮУЮУЮЮУЮАОЮУЮАIА, КЛОУЮЮУЮЮУЮУЮЮУЮАIАУЮЮУЮЮУЮУЮЮУЮЮУЮУЮЮУЮУЮЮУЮЮУЮУЮЮУЮАЮУЮУЮЮУЮУЮЮУЮЮУЮУЮЮУЮУЮЮУЮЮУЮУЮЮУЮЮУЮУЮЮУЮУЮЮУЮЮУЮУЮЮУЮ!"
```