// Mise en place du framework express
const express = require("express");
// Importation de nos routes
const routes = require("./Routes/routes");
// Mise en place des headers
const cors = require("cors");

const API_PORT = 9000;

const app = express();
app.use(express.json());
app.use("/api", routes);

app.listen(API_PORT, () => {
  console.log("Server is running !");
  console.log("> Port\t: " + API_PORT);
  console.log("> URL\t: http://localhost:" + API_PORT + "/api/hc");
});
