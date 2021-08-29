import express from "express"
import { UserController } from "../Controllers"
import { ErrorCodeGenerator } from "../Security"

const router = express.Router()
const userController = new UserController()
const httpError = new ErrorCodeGenerator()

/**
 *  [GET all users async route]
 *  @return                [status + response]
 */
router.get("/", async (_req, res) => {
  try {
    const response = await userController.retrive_all()
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
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
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET one user by id async route]
 *  @return               [status + response]
 */
router.get("/:user", async (req, res) => {
  try {
    const response = await userController.retrive_one(req.params.user)
    return res.status(200).send(response)  
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET one user by its phone number async route]
 *  @return               [status + response]
 */
 router.get("/phone/:phone", async (req, res) => {
  try {
    const response = await userController.retrive_one_by_phone_number(req.params.phone)
    return res.status(200).send(response)  
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [PUT one user by id async route]
 *  @return               [status + response]
 */
router.put("/:user", async (req, res) => {
  try {
    const response = await userController.update_one(req.params.user, req.body)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [DELETE one user by id async route]
 *  @return               [status + response]
 */
router.delete("/:user", async (req, res) => {
  try {
    const response = await userController.delete_one(req.params.user)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

export default router
