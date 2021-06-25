import express from "express"
import UserController from "../Controllers/UserController"
import { StringVerification } from "../Security"

const router = express.Router()

// GET All Users
router.get("/", async (req, res) => {
  const userController = new UserController()
  const response = await userController.getAllUsers()
  return res.send(response)
})

// GET User by id
router.get("/:id", async (req, res) => {
  const userController = new UserController()
  const verification = new StringVerification()
  let response = null

  if (verification.verifyIdRequest(req.params.id) {
    response = await userController.getUserById(req.params.id)
  } else {
    response = await userController.getUserByUsername(req.params.id)
  }

  if (!response) {
    return res.status(404).send({ message: "User " + req.params.id + " not found." })
  } else {
    return res.send(response)
  }
})

// POST new User
router.post("/", async (req, res) => {
  const userController = new UserController()
  const response = await userController.createUser(req.body)
  return res.send(response)
})

export default router
