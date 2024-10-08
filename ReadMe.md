### Comment démarrer le projet ?

Mettre des données de simulations tel que décrite dans la spécification dans le fichier mow_simulation et lancer la méthode Main de la classe MowItNowApplication.

### Question sur la spécification ?

1- Est ce que les coordonnées peuvent être négatives ?

La décision a été prise que non et donc des contrôles ont été pris en conséquence pour l'empêcher.

2- Est ce que les tondeuses se gênent mutuellement ?

La décision a été prise que non, mais le code peut être modifier pour prendre en compte ce probléme et éviter la destruction de tondeuse.

3- Contrainte dans les librairies à utiliser ?

Le projet n'a pris que JUnit et AssertJ pour la partie test et s'est limité à ces deux librairies.

Le projet doit présenter une éxécution en mode Spring-batch.
Le package batch contient tout le code nécessaire pour lancer le projet en mode spring-batch.
Il contient aussi un filePoller qui sera démarrer par Spring.
Il scrutera un repertoire in, placera le résultat du traitement dans out suffixé avec l'id du job, puis déplacera le fichier d'entrée dans done en le suffixant avec l'id du job.
Si une erreur se présente lors du traitement, le fichier sera déplace dans done en le suffixant avec ERROR, une exception sera loggué.

Les dossier in, out et done sont paramétrable dans le fichier de properties du projet.
