#!/bin/bash

function get_docker() {
  # Installation de Docker sur la machine
  echo "-> Installation of Docker";
  docker_prerequistes;
  docker_installation;
  docker_configuration;
  echo "-> Docker installation complete";
}

function docker_prerequistes() {
  echo -e "\n\tInstalling a few prerequisite packages which let apt use packages over HTTPS...";
  apt install -yqq apt-transport-https\
                 ca-certificates\
                 curl\
                 gnupg2\
                 software-properties-common;

  echo -e "\n\tAdd the GPG key for the official Docker repository...";
  curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -;

  echo -e "\n\tAdd the Docker repository to APT sources...";
  add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable";
}

function docker_installation() {
  echo -e "\n\tRunning apt update...";
  apt update -yqq;
  echo -e "\n\tInstalling Docker...";
  apt install -yqq docker-ce;
}

function docker_configuration() {
  echo -e "\n\tGiving to $username the right to use Docker without sudo...";
  /sbin/usermod -aG docker "$username";
}
