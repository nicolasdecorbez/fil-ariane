import express from "express"
import { UserController } from "../Controllers"

const router = express.Router()
const userController = new UserController()

/**
 *  [GET all users async route]
 *  @// TODO:  [add try/catch methods]
 *  @return                [status + response]
 */
router.get("/", async (_req, res) => {
  const response = await userController.retrive_all()
  return res.status(200).send(response)
})

/**
 *  [POST new user async route]
 *  @return               [status + response]
 */
router.post("/", async (req, res) => {
  try {
    const response = await userController.create(req.body)
    return res.status(201).send(response)
  } catch (error) {
    return res.status(418).send(error)
  }
})

/**
 *  [GET one user by id or username async route]
 *  @// TODO:  [add try/catch methods]
 *  @return               [status + response]
 */
router.get("/:user", async (req, res) => {
  const response = await userController.retrive_one(req.params.user)
  if (!response) {
    return res.status(404).send({ message: "User " + req.params.user + " not found." })
  }
  return res.status(200).send(response)
})

/**
 *  [PUT one user by id or username async route]
 *  @// TODO:  [add try/catch methods]
 *  @return               [status + response]
 */
router.put("/:user", async (req, res) => {
  const response = await userController.update_one(req.params.user, req.body)
  if (!response) {
    return res.status(404).send({ message: "User " + req.params.user + " not found." })
  }
  return res.status(200).send(response)
})

/**
 *  [DELETE one user by id or username async route]
 *  @// TODO:  [add try/catch methods]
 *  @return               [status + response]
 */
router.delete("/:user", async (req, res) => {
  const response = await userController.delete_one(req.params.user)
  if (!response) {
    return res.status(404).send({ message: "User " + req.params.user + " not found." })
  }
  return res.status(200).send(response)
})

export default router
