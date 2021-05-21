#!/bin/bash

source src/sources_includer.sh;            # Importation de toutes nos fonctions

root_test;
start_configuration;
user_creator;
create_deploy_dir;
get_docker;
get_docker-compose;
end_configuration;
