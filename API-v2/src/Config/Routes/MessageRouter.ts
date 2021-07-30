import express from "express"
import { MessageController } from "../Controllers"

const router = express.Router()
const messageController = new MessageController()

// GET All Users
router.get("/", async (req, res) => {
  const response = await messageController.retrive_all()
  return res.status(200).send(response)
})

// POST new User
router.post("/", async (req, res) => {
  try {
    const response = await messageController.create(req.body)
    return res.status(201).send(response)
  } catch (error) {
    return res.status(418).send(error)
  }
})

export default router
