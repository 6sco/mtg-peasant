# mtg-peasant back-end

* Installation standard de [mongoDB Community Edition](https://docs.mongodb.com/manual/installation/#mongodb-community-edition)

* Lancer l'application:
    * java - jar mtg-peasant-backend-1.0.0-SNAPSHOT.jar
    * Depuis la classe Application avec un IDE

Si il n'y a pas de cartes dans mongodb, l'application récupère (environ 5 minutes) la base de cartes au démarrage.
La mise à jour des cartes s'éffectue automatiquement une fois par semaine.

---

* Sur IntelliJ je recommande l'installation des plugins:
    * Handlebars/Mustache (pour l'édition des fichiers [Mustache](http://mustache.github.io/)
    utilisés par [Spring RestDocs](http://spring.io/projects/spring-restdocs) pour modéliser la documentation).
    * AsciiDoc (pour l'édition des fichiers [adoc](https://asciidoctor.org/) 
    utilisés par [Spring RestDocs](http://spring.io/projects/spring-restdocs) pour générer la documentation).
    * Lombok Plugin (pour l'utilisation du framework [Lombok](https://projectlombok.org)).



