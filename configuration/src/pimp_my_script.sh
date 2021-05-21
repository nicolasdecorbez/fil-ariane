#!/bin/bash

# Déclaration ici de plusieurs éléments graphiques pour l'UI

function start_configuration() {
  echo;
  echo "########################################";
  echo "#                                      #";
  echo "#       First Configuration for        #";
  echo "#            Deployers VMs.            #";
  echo "#                                      #";
  echo "#      Please refer to README.md       #";
  echo "#        for more instructions.        #";
  echo "#                                      #";
  echo "########################################";
  echo;
}

function end_configuration() {
  red=`tput setaf 1`
  green=`tput setaf 2`
  reset=`tput sgr0`

  echo;
  echo "########################################";
  echo "#                                      #";
  echo "#       Configuration complete!        #";
  echo "#                                      #";
  echo "#    You're now able to use Docker     #";
  echo "#          with this user :            #";
  echo "#  ${green}Username${reset} : deployer                 #";
  echo "#  ${red}Password${reset} : deployer                 #";
  echo "#                                      #";
  echo "########################################";
  echo;
}
