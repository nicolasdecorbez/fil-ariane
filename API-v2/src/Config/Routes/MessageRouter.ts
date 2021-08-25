import express from "express"
import { MessageController } from "../Controllers"
import { ErrorCodeGenerator } from "../Security"

const router = express.Router()
const messageController = new MessageController()
const httpError = new ErrorCodeGenerator()

/**
 *  [GET all messages async route]
 *  @return                [status + response]
 */
router.get("/", async (_req, res) => {
  try {
    const response = await messageController.retrive_all()
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
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
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET one message by id async route]
 *  @return               [status + response]
 */
router.get("/:message", async (req, res) => {
  try {
    const response = await messageController.retrive_one(req.params.message)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET all messages from a receiver async route]
 *  @return                [status + response]
 */
router.get("/receiver/:id", async (req, res) => {
  try {
    const response = await messageController.retrieve_all_from_receiver(req.params.id)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET all messages from a sender async route]
 *  @return                [status + response]
 */
router.get("/sender/:id", async (req, res) => {
  try {
    const response = await messageController.retrieve_all_from_sender(req.params.id)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

export default router
