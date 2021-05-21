#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100) UNIQUE,
  name VARCHAR(100),
  firstname VARCHAR(100),
  phone VARCHAR(10),
  email VARCHAR(255)
);
INSERT INTO users (username, name, firstname, phone, email) VALUES ('decoconicoco', 'decorbez', 'nicolas', '0770581154', 'decorbezpro@gmail.com');
EOSQL
