import express from "express"
import { FriendsController } from "../Controllers"
import { ErrorCodeGenerator } from "../Security"

const router = express.Router()
const friendsContoller = new FriendsController()
const httpError = new ErrorCodeGenerator()

/**
 *  [POST new friends async route]
 *  @return               [status + response]
 */
router.post("/", async (req, res) => {
  try {
    const response = await friendsContoller.create(req.body)
    return res.status(201).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET all friends by one id async route]
 *  @return               [status + response]
 */
 router.get("/:friend", async (req, res) => {
  try {
    const response = await friendsContoller.retrieve_all_friends(req.params.friend)
    return res.status(200).send(response)  
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

export default router