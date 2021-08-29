import express from "express"
import UserRouter from "./UserRouter"
import MessageRouter from "./MessageRouter"
import JourneyRouter from "./JourneyRouter"
import FriendsRouter from "./FriendsRouter"

/**
 *  [Create initial router and import child routers]
 */
const router = express.Router()
router.use("/users", UserRouter)
router.use("/messages", MessageRouter)
router.use("/journeys", JourneyRouter)
router.use("/friends", FriendsRouter)

export default router
