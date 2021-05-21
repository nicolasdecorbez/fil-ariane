const express = require("express");
const Pool = require("pg").Pool;

// Creation de notre router
const router = express.Router();

// HTTP Response codes
const HTTP_OK = 200;
const HTTP_CREATED = 201;
const HTTP_NO_CONTENT = 204;

// Database information
const DATABASE_HOST = process.env["DATABASE_HOST"] || "database";
const DATABASE_NAME = process.env["DATABASE_NAME"] || "ardiane";
const DATABASE_USER = process.env["DATABASE_USER"] || "ardiane";
const DATABASE_PASSWORD = process.env["DATABASE_PASSWORD"] || "P4$$w0rD";

// Définition des information de notre database
const pool = new Pool({
  user: DATABASE_USER,
  host: DATABASE_HOST,
  database: DATABASE_NAME,
  password: DATABASE_PASSWORD,
  port: 5432,
});

// Définition des queries
const insert_user = "INSERT INTO users (username, name, firstname, phone, email) VALUES ($1, $2, $3, $4, $5);";

const get_users = "SELECT * FROM users";
const get_user_by_username = get_users + " WHERE username = $1";

const delete_user_by_username = "DELETE FROM users WHERE username = $1"

// Health Check
router.get("/hc", async (req,res) => {
  try {
    res.status(HTTP_OK).send("All systems OK\n");
  } catch (error) {
    res.send(error);
  }
});

router.get("/users", async (req,res) => {
  const client = await pool.connect();
  try {
    const users = await client.query(get_users);
    res.status(HTTP_OK).send(users);
  } catch (error) {
    res.send(error);
  } finally {
    client.release();
  }
});

router.get("/user/:username", async (req,res) => {
  const client = await pool.connect();
  const query = {
    text: get_user_by_username,
    values: [ req.params.username ]
  };

  try {
    const users = await client.query(query);
    res.status(HTTP_OK).send(users);
  } catch (error) {
    res.send(error);
  } finally {
    client.release();
  }
});

router.post("/user", async (req,res) => {
  const client = await pool.connect();
  const query = {
    text: insert_user,
    values: [
      req.body.username,
      req.body.name,
      req.body.firstname,
      req.body.phone,
      req.body.email
    ]
  };

  try {
    const user = await client.query(query);
    res.status(HTTP_CREATED).send(user);
  } catch (error) {
    res.send(error);
  } finally {
    client.release();
  }
});

router.delete("/products/:username", async (req,res) => {
  const client = await pool.connect();
  const query = {
    text: delete_user_by_username,
    values: [ req.params.username ]
  };
  try {
    await client.query(query);
    res.status(HTTP_NO_CONTENT).send();
  } catch (error) {
    res.send(error);
  } finally {
    client.release();
  }
});

module.exports = router;
