import express from "express"
import { JourneyController } from "../Controllers"
import { ErrorCodeGenerator } from "../Security"

const router = express.Router()
const journeyController = new JourneyController()
const httpError = new ErrorCodeGenerator()

/**
 *  [GET all journeys async route]
 *  @return                [status + response]
 */
router.get("/", async (_req, res) => {
  try {
    const response = await journeyController.retrive_all()
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [POST new journey async route]
 *  @return               [status + response]
 */
router.post("/", async (req, res) => {
  try {
    const response = await journeyController.create(req.body)
    return res.status(201).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET one journey by id async route]
 *  @return               [status + response]
 */
router.get("/:journey", async (req, res) => {
  try {
    const response = await journeyController.retrive_one(req.params.journey)
    return res.status(200).send(response)  
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [PUT one journey by id or journeyname async route]
 *  @return               [status + response]
 */
 router.put("/:journey", async (req, res) => {
  try {
    const response = await journeyController.update_one(req.params.journey, req.body)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [DELETE one journey by id async route]
 *  @return               [status + response]
 */
router.delete("/:journey", async (req, res) => {
  try {
    const response = await journeyController.delete_one(req.params.journey)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET all journeys from a theseus async route]
 *  @return                [status + response]
 */
router.get("/theseus/:id", async (req, res) => {
  try {
    const response = await journeyController.retrieve_all_from_theseus(req.params.id)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [PUT one journey by id or journeyname async route]
 *  @return               [status + response]
 */
 router.put("/theseus/:id", async (req, res) => {
  try {
    const response = await journeyController.update_all_from_theseus(req.params.id, req.body)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [DELETE all journeys from a theseus async route]
 *  @return                [status + response]
 */
 router.delete("/theseus/:id", async (req, res) => {
  try {
    const response = await journeyController.delete_all_of_theseus(req.params.id)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

/**
 *  [GET one journey with a pair of Theseus and Ardiane ids async route]
 *  @return               [status + response]
 */
router.get("/theseus/:theseusId/ardiane/:ardianeId", async (req, res) => {
  try {
    const response = await journeyController.get_pair(req.params.ardianeId, req.params.theseusId)
    return res.status(200).send(response)
  } catch (error) {
    const code = httpError.readError(error)
    return res.status(code).send(error)
  }
})

export default router