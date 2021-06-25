import "reflect-metadata"

import { createConnection } from "typeorm"
import express from "express"
// import cors from "cors"
import bodyParser from "body-parser"

import Router from "./Config/Routes"
import dbConfig from "./Config/database"


const API_PORT = process.env.API_PORT || 9000

const app = express()
app.use(express.json())
app.use(bodyParser.urlencoded({ extended: true }))

app.use(Router)

createConnection(dbConfig)
  .then( () => {
    app.listen(API_PORT, () => {
      console.log("Server is running !\n> PORT\t: " + API_PORT)
    })
  })
  .catch((error) => {
    console.log("Unable to connect to db : \n" + error)
    process.exit(1)
  })
