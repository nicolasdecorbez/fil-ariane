import { DeleteResult, getRepository } from "typeorm"
import { JourneyModel } from "../Models"

/**
 * [generate an error message if the id of the type doesn't exists]
 * @param type    [the type of the ressource]
 * @param id      [the id of the ressource]
 */
function ErrorNotFound(type: string, id: string) {
  this.name = "NotFoundError"
  this.journey = type + " " + id + " not found."
}

/**
 *  payload definition for incomming requests
 */
export interface JourneyRequest {
  ardianeId: number
  theseusId: number
  latitude: string
  longitude: string
  returnDate: string
}

/**
 *  payload definition for database writing
 */
export interface JourneySchema {
  ardianeId: number
  theseusId: number
  location: string
  returnDate: string
}

/**
 *  [async function to retrieve all journeys]
 *  @return [a promise with an array of JourneyModel]
 */
export const getAllJourneys = async (): Promise<JourneyModel[]> => {
  const journeyRepository = getRepository(JourneyModel)
  return journeyRepository.find()
}

/**
 *  [async function to create a new journey]
 *  @param  request [JourneySchema with data]
 *  @return         [a promise with the created journey]
 */
 export const createJourney = async (
  request: JourneySchema
): Promise<JourneyModel> => {

  const journeyRepository = getRepository(JourneyModel)
  const journey = new JourneyModel()
  return journeyRepository.save({
    ...journey,
    ...request,
  })
}

/**
 *  [async function to retrieve one journey]
 *  @param  request     [id of the journey to find]
 *  @return             [a promise with a JourneyModel]
 */
export const getOneJourney = async (
  request: string
): Promise<JourneyModel> => {
  
  const journeyRepository = getRepository(JourneyModel)
  let journey = null
  journey = await journeyRepository.findOne({id: Number(request)})
  
  if(!journey)
  {
    throw new ErrorNotFound("Journey", request) as Error
  }
  return journey
}

/**
 *  [async function to update one journey]
 *  @param  request     [id of the journey to update]
 *  @param  data        [JourneySchema with changes]
 *  @return             [a promise with an updated JourneyModel]
 */
export const updateOneJourney = async (
  request: string,
  data: JourneySchema
): Promise<JourneyModel> => {

  const journeyRepository = getRepository(JourneyModel)
  const journey = await journeyRepository.findOne({ id: Number(request) })

  if(!journey)
  {
    throw new ErrorNotFound("Journey", request) as Error
  }

  journeyRepository.merge(journey, data)
  return journeyRepository.save(journey)
}

/**
 *  [async function to update all journeys for one theseus]
 *  @param  request     [id of the theseus to update]
 *  @param  data        [JourneySchema with changes]
 *  @return             [a promise with an array of updated JourneyModel]
 */
export const updateAllJourneyFromThesus = async (
  request: string,
  data: JourneySchema
): Promise<JourneyModel[]> => {

  const journeyRepository = getRepository(JourneyModel)
  const journeys = await journeyRepository.find({ 
    where: {
      theseusId: Number(request) 
    }
  })

  if(journeys.length < 1)
  {
    throw new ErrorNotFound("Journey", request) as Error
  }

  for await (const journey of journeys) {
    journeyRepository.merge(journey, data)
    journeyRepository.save(journey)
  }

  return journeys
}

/**
 *  [async function to delete one journey]
 *  @param  request     [id of the journey to delete]
 *  @return             [JourneyModel with affected journey]
 */
export const deleteOneJourney = async (
  request: string
): Promise<DeleteResult> => {

  const journeyRepository = getRepository(JourneyModel)
  const result = await journeyRepository.delete({
     id: Number(request) 
  })

  if (result.affected < 1)
  {
    throw new ErrorNotFound("Journey", request) as Error
  }
  return result
}

/**
 *  [async function to retrieve all journeys from a theseus]
 *  @param  request     [id of the theseus to find]
 *  @return             [a promise with an array of JourneyModel]
 */
export const getAllJourneysFromTheseus = async (
  request: string
): Promise<JourneyModel[]> => {
  
  const journeyRepository = getRepository(JourneyModel)
  const journey = await journeyRepository.find({
    where: {
       theseusId: Number(request)
    }
  })

  if(journey.length < 1)
  {
    throw new ErrorNotFound("Journeys from Theseus", request) as Error
  }
  return journey
}

/**
 *  [async function to delete all journey of a theseus]
 *  @param  request     [id of the theseus]
 *  @return             [DeletedResult with affected journey]
 */
export const deleteAllJourneyOfTheseus = async (
  request: string
): Promise<DeleteResult> => {

  const journeyRepository = getRepository(JourneyModel)
  const result = await journeyRepository.delete({
     theseusId: Number(request) 
  })

  if (result.affected < 1)
  {
    throw new ErrorNotFound("Journeys for Theseus", request) as Error
  }
  return result
}

/**
 *  [async function to retrieve one journey with both Theseus and Ardiane ids]
 *  @param ardianeReq [id of the ardiane to find]
 *  @param theseusReq [id of the theseus to find]
 *  @returns          [a promise with the JourneyModel]
 */
export const getPairOfTheseusAndArdiane = async (
  ardianeReq: string,
  theseusReq: string
): Promise<JourneyModel> => {
  
  const journeyRepository = getRepository(JourneyModel)
  let journey = null
  journey = await journeyRepository.findOne({
    ardianeId: Number(ardianeReq),
    theseusId: Number(theseusReq)
  })

  if(!journey)
  {
    throw new ErrorNotFound(
      "Journey for Theseus " + theseusReq + " and Ardiane",
       ardianeReq
    ) as Error
  }

  return journey
}