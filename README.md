# Repo temporaire de l'API

### Si vous avez accès à ce repo, vous êtes la résistance.

Nan plus sérieusement je vais mettre ici toutes mes avancées sur l'API. C'est ici aussi qu'on va voir les différentes bonnes habitudes à chopper (commit-msg, .gitignore, etc)

Aussi y'aura plein de liens utiles dont vous aurez besoin histoire qu'on ruine ce PLI bien comme il faut. Puis vous être libre de rajouter des choses dessus, mais on verra les PR et review un peu plus tard.

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
$ docker run -d -p 8080:8080 secure-api/local
```

## Test

En vous rendant à l'adresse <http://localhost:8080/api/bob> vous devriez voir le message suivant apparaître :
```
Bob the builder can break your step
```

Sinon, go discord et on voit ça ensemble.

## Liens

- [HTTP Return codes](https://github.com/for-GET/http-decision-diagram) (merci @tbobm)
- [Git Commit msg](https://karma-runner.github.io/0.10/dev/git-commit-msg.html)
- [Docker installation](https://docs.docker.com/get-docker/)
