CREATE TABLE users {
  id INT PRIMARY KEY NOT NULL,
  nom VARCHAR(100),
  prenom VARCHAR(100),
  email VARCHAR(255),
}

INSERT INTO users VALUES (1, 'decorbez', 'nicolas', 'decorbezpro@gmail.com');
