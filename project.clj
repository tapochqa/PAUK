(defproject org.clojars.tapochqa/pauk "1.3.0"
  :description "Translates any latin or cyrillic text to РАЮК."
  :url "https://github.com/tapochqa/PAUK"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :repl-options {:init-ns pauk.core}
  :main pauk.core)