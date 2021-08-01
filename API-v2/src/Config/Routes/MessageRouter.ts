import express from "express"
import { MessageController } from "../Controllers"

const router = express.Router()
const messageController = new MessageController()

/**
 *  [GET all messages async route]
 *  @// TODO:  [add try/catch methods]
 *  @return                [status + response]
 */
router.get("/", async (_req, res) => {
  const response = await messageController.retrive_all()
  return res.status(200).send(response)
})

/**
 *  [POST new message async route]
 *  @return               [status + response]
 */
router.post("/", async (req, res) => {
  try {
    const response = await messageController.create(req.body)
    return res.status(201).send(response)
  } catch (error) {
    return res.status(418).send(error)
  }
})

export default router
