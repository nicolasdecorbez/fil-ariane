#!/bin/sh

function get_docker-compose() {
  # Installation de docker-compose sur la machine
  echo "-> Installation of docker-compose";

  # Récupération de la version 1.29.1 de docker-compose
  curl -L https://github.com/docker/compose/releases/download/1.29.1/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose

  # Création de l'executable
  chmod +x /usr/local/bin/docker-compose

  echo "-> Docker installation complete";
}
