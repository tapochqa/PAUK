# РАЮК

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.tapochqa/pauk.svg)](https://clojars.org/org.clojars.tapochqa/pauk)

Translates any latin or cyrillic text to РАЮК.


## Installation

```clojure 

;; project.clj

:dependencies [[org.clojars.tapochqa/pauk "1.2.0"]]

;; core.clj

(ns. project.core
	(:require [pauk.core :as pauk]))

```


## Paukization

```clojure
project.core=> (pauk/paukize "Hello, World!")
"НЕЛЛО, ШОЯЛД!"
project.core=> (pauk/paukize "Здарова, кложары!")
"ПДАЯОФА, КЛОПНАЯУ!"
```