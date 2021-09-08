# API v2

On a ici notre deuxième version de notre main API. Comparé à la `v1`, on a implémenté plusieurs fonctionnalités dans cette dernière :
- Utilisation d'un [ORM](https://blog.bitsrc.io/what-is-an-orm-and-why-you-should-use-it-b2b6f75f5e2a) : *[typeorm](https://typeorm.io/#/)*. Il va surtout nous servir à attaquer notre DB plus facilement en créant :
  - Des [Models](src/Models) : ce sont nos schéma de base de données.
  - Des [Repositories](src/Repositories) : nos méthodes pour attaquer notre DB.
  - Des [Controllers](src/Controllers) : fais le lien entre nos *Routes* et nos *Repositories*.
  - Des [Routes](src/Routes) : permettent d'appeler un *Controller* en fonction de la route appelée.
  - Des [Security](src/Security) : afin de vérifier la data qu'on reçoit, afin de savoir si elle est conforme ou non.
- Remplacement du JavaScript par du [TypeScript](https://dzone.com/articles/what-is-typescript-and-why-use-it) afin de faciliter l'écriture de notre code.
- Et évidement un [linter](https://www.freecodecamp.org/news/what-is-linting-and-how-can-it-save-you-time/), plus précisement [ESLint](https://eslint.org/) avec un [plugin](https://github.com/typescript-eslint/typescript-eslint) pour du TypeScript.

## Installation et utilisation

Vous pouvez lancer cette API avec `docker` ou `npm`. Je recommande néanmoins l'utilisation de `docker-compose` afin de lancer la DB en même temps, avec les bons paramètres.

:warning: -> Si vous décidez malgré tout de lancer cette API sans `docker-compose`, vous êtes responsable du lancement de la DB (ici, [PostgreSQL](https://www.postgresql.org/download/)) et de sa configuration.
Une fois configurée et avant le lancement de l'API, prenez soin de setup les variables d’environnement suivantes pour l'API :
- `DATABASE_HOST` (default : `localhost`)
- `DATABASE_PORT` (default : `5432`)
- `DATABASE_DB` (default : `postgres`)
- `DATABASE_USER` (default : `postgres`)
- `DATABASE_PASSWORD`(default : `postgres`)

> Pour setup des variables d'environnement avec `docker run`, il vous suffit d'utiliser le flag [`-e`](https://docs.docker.com/engine/reference/commandline/run/#options)

Avec `docker-compose`, tout ce travail est fait automatiquement ; c'est pour ça que je vous encourage à l'utiliser dans un premier temps.

## Test des routes

L'API entière est testé lors du processus de [*Continuous integration*](https://en.wikipedia.org/wiki/Continuous_integration) grâce à une collection [Postman](https://www.postman.com/) disponible [**ici**](https://www.getpostman.com/collections/e33ad8d28e164e80aed5). Vous pouvez voir les résultats de ce test, nommé *`Routes check`*, dans la partie `Actions` de GitHub.

> La collection Postman base du une variable `host`. Pensez à l'adapter à vos besoin pour pouvoir accéder aux routes.
