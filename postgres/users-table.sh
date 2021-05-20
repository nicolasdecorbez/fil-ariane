#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE TABLE users (
  id INT PRIMARY KEY NOT NULL,
  nom VARCHAR(100),
  prenom VARCHAR(100),
  email VARCHAR(255),
);
INSERT INTO users VALUES (1, 'decorbez', 'nicolas', 'decorbezpro@gmail.com');
EOSQL
