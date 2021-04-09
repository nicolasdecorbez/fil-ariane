# Repo temporaire de l'API

### Si vous avez accès à ce repo, vous êtes la résistance.

Nan plus sérieusement je vais mettre ici toutes mes avancées sur l'API. C'est ici aussi qu'on va voir les différentes bonnes habitudes à chopper (commit-msg, .gitignore, etc)

Aussi y'aura plein de liens utiles dont vous aurez besoin histoire qu'on ruine ce PLI bien comme il faut. Puis vous être libre de rajouter des choses dessus, mais on verra les PR et review un peu plus tard.

## Mise en place de la DB (*temporaire*)

Pour mettre en place **MongoDB**, je recommande de la faire tourner par *Docker*. Sinon installez là en local :

### Avec `apt`
Un bordel à mettre en place : [lien pour l'installation](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)

### Avec `Docker`
```console
$ docker run -d -p 27017:27017 mongo
```

## Installation de l'API

Commencez d'abord par cloner ce repo pour avoir les fichiers en local.

### Avec `npm`
```console
$ npm install
$ npm start
```

### Avec `docker`
```console
$ docker build -t secure-api/local .
$ docker run -d --network=host secure-api/local
```
> :warning: `--network=host` permet la connexion à un mongodb en local sur VOTRE machine. Elle sera retirée au moment où la DB sera déployé dans un container également
>
> Aussi, ça annule la redirection de port, qu'on aurait écrit `-p 8080:8080`. Ce sera remis en place à l'avenir.

## Test

En vous rendant à l'adresse <http://localhost:8080/api/bob> vous devriez voir le message suivant apparaître :
```console
nico@nico-dev [12:06:32] [~]
-> $ curl http://localhost:8080/api/bob

Bob the builder can break your step
```

Sinon, go discord et on voit ça ensemble.

## Liens

- [HTTP Return codes](https://github.com/for-GET/http-decision-diagram) (merci @tbobm)
- [Git Commit msg](https://karma-runner.github.io/0.10/dev/git-commit-msg.html)
- [Docker installation](https://docs.docker.com/get-docker/)
