#!/bin/bash

source src/pimp_my_script.sh;     # Outils d'affichage pour embélir l'UI.
source src/root_test.sh;          # Check si on a bien les droits root.
source src/user_creator.sh;       # Création d'un nouvel utilisateur.
source src/create_deploy_dir.sh;  # Création du dossier pour le deploy.
source src/get_docker.sh;         # Installation de Docker.
source src/get_docker-compose.sh; # Installation de docker-compose.
