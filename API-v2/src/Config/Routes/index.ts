import express from "express"
import UserRouter from "./UserRouter"
import MessageRouter from "./MessageRouter"

/**
 *  [Create initial router and import child routers]
 */
const router = express.Router()
router.use("/users", UserRouter)
router.use("/messages", MessageRouter)

export default router
