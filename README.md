# Ardiane's Thread API

## What is this ? 

This is a repository for our end-of-year project at ETNA, **Ardiane's Thread**.

The API, written in [TypeScript](https://github.com/microsoft/TypeScript), it is based on the [Express](https://github.com/expressjs/express) framework ; using [TypeORM](https://github.com/typeorm/typeorm), it connects to a [PostgreSQL](https://github.com/postgres/postgres) database. We also have implemented [CORS](https://www.npmjs.com/package/cors) managment for all of our routes.

## Installation

To run the API, you must setup your (or a virtual) machine based on Debian 10. To do so, we provided you a configuration script, located in the [configuration](./configuration/) folder. 

> We provided a full guide to adapt the script to your needs. You can find it [here](./configuration/README.md)

This done, the API can be launched by running `docker-compose up` at the root of this project. If it is not the first time you run this project, please be aware to run `docker-compose build` to run the latest version available.

> For more informations, you can refer to the [API documentation](./API-v2/README.md). 

## Stack description

For the API, we implemented 3 different services : 
- The API
- A PostgreSQL database
- A Traefik instance as a reverse-proxy

## CI / CD

To automate the code testings, we implemented 2 workflows : 
- The [first one](.github/workflows/linter.yml) checks if the linter is respectd.
- The [second one](.github/workflows/routes.yml) checks if all the routes are functionals. 

The second one is only launched on *push* or *pull-request* to the branch *master*. Then, if theses checks passed, we push the Docker image of the API to the [Docker Hub registery](https://hub.docker.com/r/ardianethread/ardiane_api) using the [CD workflow](.github/workflows/docker.yml).