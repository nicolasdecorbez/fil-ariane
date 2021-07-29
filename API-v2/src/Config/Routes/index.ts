import express from "express"
import UserRouter from "./UserRouter"
import MessageRouter from "./MessageRouter"

const router = express.Router()
router.use("/users", UserRouter)
router.use("/messages", MessageRouter)

export default router
