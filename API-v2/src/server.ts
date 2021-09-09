import "reflect-metadata"

import { createConnection } from "typeorm"
import express from "express"
import cors from "cors"

import Router from "./Config/Routes"
import dbConfig from "./Config/database"


const API_PORT = process.env.API_PORT || 9000
const app = express()

/**
 *  CORS configuration allowing every origins 
 */
// const allowedOrigins = ["*"]
// const options: cors.CorsOptions = {
//   origin: allowedOrigins,
  
// }

app.use(cors())
app.options("*", cors())

app.use(express.json())
app.use(express.urlencoded({ extended: true }))
app.use(Router)

/**
 *  [create connection to database and launch the server]
 *  @param  dbConfig    [database configuration]
 */
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
