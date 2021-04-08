const express = require("express");
const router = express.Router();

// HTTP Response codes
const HTTP_OK = 200;
const HTTP_CREATED = 201;
const HTTP_NO_CONTENT = 204;

const Contact = require("../Models/Contact");

// Route de test pour check la mise en place
router.get("/bob", async (req,res) => {
  try {
    res.status(HTTP_OK).send("Bob the builder can break your step\n");
  } catch (err) {
    res.send(err);
  }
});

module.exports = router;
