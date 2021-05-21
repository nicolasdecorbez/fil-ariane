#!/bin/bash

function user_creator() {
  # Création d'un nouvel utilisateur.
  echo "-> Creation of the new user"

  # On lit dans le fichier user.conf les informations de user
  username=$(cat src/conf.d/user.conf | grep -e "USERNAME" | cut -d "=" -f 2);
  password=$(cat src/conf.d/user.conf | grep -e "PASSWORD" | cut -d "=" -f 2);

  # On cherche dans passwd si l'username existe
  user_exists=$(getent passwd "$username" | cut -d ":" -f 1);

  # S'il n'existe pas, alors on le crée
  if [[ $user_exists != "$username" ]]; then
    create_user;
  else
    echo "User $username already exists. Skiping creation.";
  fi
}

function create_user() {
  # Une fois toutes les informations récupérées, on crée notre utilisateur.
  /sbin/useradd -s "/bin/bash" -m "$username";

  # On ajoute le mot de passe.
  echo "$username:$password" | /sbin/chpasswd;
  echo "-> User $username created."
}
