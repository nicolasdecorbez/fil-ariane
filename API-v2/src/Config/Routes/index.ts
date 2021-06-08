import express from "express"
import UserRouter from "./UserRouter"

const router = express.Router()
router.use("/users", UserRouter)

export default router
