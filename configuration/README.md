# Script de pré-configuration
*decorb_n & kechit_r*

Ce script est destiné à reproduire l'environnement de travail qui nous a été proposé pour ce sujet.

## Fonctionnalités
- Création d'un utilisateur (voir ci-dessous pour la configuration).
- Création d'un dossier `/srv` et changement d'*owner*.
- Installation de `docker` et `docker-compose`.
- Configuration pour que l'utilisateur utilise `docker` sans `sudo`.

## Prérequis

- Une machine tournant sous [Debian Buster](https://www.debian.org/releases/buster/), avec un accès **root**.
- Une connexion internet.

> Il est recommandé de faire tourner ce script sur un système neuf ; néanmoins, vous pouvez quand même le lancer sans problème sur votre système.

## Configuration de l'utilisateur et du mot de passe

Vous trouverez dans le dossier `src/conf.d` un fichier de configuration pour l'utilisateur, [`user.conf`](./src/conf.d/user.conf) :
```bash
# -------------------------------------- #
# Configuration file for user_creator.sh #
# -------------------------------------- #

# Set-up the username
USERNAME=deployer

# Set-up the password
PASSWORD=deployer

```

Vous pouvez donc, dans ce fichier, adapter les paramètre de l'utilisateur à créer.

## Lancement du script

Une fois le script récupéré sur votre machine, vous pouvez le lancer avec la commande :
```console
# bash script.sh
```

> :warning: : le script **DOIT** être lancé en tant que **root** ; sinon, le script se fermera sans rien faire.
