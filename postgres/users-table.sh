#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100),
  name VARCHAR(100),
  firstname VARCHAR(100),
  phone VARCHAR(10),
  email VARCHAR(255)
);
INSERT INTO users (nom, prenom, email) VALUES ('decorbez', 'nicolas', 'decorbezpro@gmail.com');
EOSQL
