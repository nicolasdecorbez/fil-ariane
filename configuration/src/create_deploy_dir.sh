#!/bin/sh

function create_deploy_dir() {
  # Création du dossier de travail
  echo "-> Creation of /srv...";
  mkdir /srv;

  echo -e "\tChanging the owner of /srv...";
  chown -Rf "$username" /srv;
  chgrp -Rf "$username" /srv;
}
