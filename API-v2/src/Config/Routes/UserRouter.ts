import express from "express"
import UserController from "../Controllers/UserController"
import { StringVerification } from "../Security"

const router = express.Router()
const userController = new UserController()
const verification = new StringVerification()

// GET All Users
router.get("/", async (req, res) => {
  const response = await userController.getAllUsers()
  return res.status(200).send(response)
})

// GET one User
router.get("/:id", async (req, res) => {
  let response = null
  if (verification.verifyIdRequest(req.params.id)) {
    response = await userController.getUserById(req.params.id)
  } else {
    response = await userController.getUserByUsername(req.params.id)
  }
  if (!response) {
    return res.status(404).send({ message: "User " + req.params.id + " not found." })
  }
  return res.status(200).send(response)
})

// POST new User
router.post("/", async (req, res) => {
  const response = await userController.createUser(req.body)
  return res.status(201).send(response)
})

// Update one User
router.put("/:id", async (req, res) => {
  let response = null
  if (verification.verifyIdRequest(req.params.id)) {
    response = await userController.updateUserById(req.params.id, req.body)
  } else {
    response = await userController.updateUserByUsername(req.params.id, req.body)
  }
  if (!response) {
    return res.status(404).send({ message: "User " + req.params.id + " not found." })
  }
  return res.status(200).send(response)
})

export default router
