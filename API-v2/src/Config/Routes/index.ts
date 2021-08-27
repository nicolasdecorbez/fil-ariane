import express from "express"
import UserRouter from "./UserRouter"
import MessageRouter from "./MessageRouter"
import JourneyRouter from "./JourneyRouter"


/**
 *  [Create initial router and import child routers]
 */
const router = express.Router()
router.use("/users", UserRouter)
router.use("/messages", MessageRouter)
router.use("/journeys", JourneyRouter)

export default router
