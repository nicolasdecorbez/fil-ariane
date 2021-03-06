import { DeleteResult } from "typeorm"
import { JourneyModel } from "../Models"
import {
    createJourney,
    deleteOneJourney,
    deleteAllJourneyOfTheseus,
    getAllJourneys,
    getAllJourneysFromTheseus,
    getOneJourney,
    JourneySchema,
    updateOneJourney,
    updateAllJourneyFromThesus,
    JourneyRequest,
    getPairOfTheseusAndArdiane
  } from "../Repositories/JourneyRepo"
import { StringVerification } from "../Security"


/**
 * [generate an error message when the id is not an int]
 * @param id    [the string with the invalid id]
 */
function BadIdRequest(id: string) {
    this.name = "QueryFailedError"
    this.journey = "Invalid ID request : " + id + " -> Must be a number."
}

/**
 *  [generate a JourneySchema from a JourneyRequest]
 * @param input [the JourneyRequest to transform]
 * @returns     [the JourneySchema to send to repo]
 */
function GenerateSchema(input: JourneyRequest): JourneySchema {
    const schema: JourneySchema = {
      ardianeId: input.ardianeId,
      theseusId: input.theseusId,
      returnDate: input.returnDate,
      location: undefined
    } 

    if (input.latitude && input.longitude) {
      schema.location = "http://www.google.com/maps/place/" + input.latitude + "," + input.longitude
    }
  
    return schema
}


export class JourneyController {

    /**
     *  [call the journey repo to retrive all journeys from database]
     *  @return         [a promise with an array of JourneyModel]
     */
    public async retrive_all(): Promise<JourneyModel[]> {
      return getAllJourneys()
    }
  
    /**
     *  [call the journey repo to create a brand new journey]
     *  @param  body    [format the sent body to a JourneySchema]
     *  @return         [a promise with the JourneySchema created]
     */
    public async create(body: JourneyRequest): Promise<JourneyModel> {
      return createJourney(GenerateSchema(body))
    }
  
    /**
     *  [check if the request is valid, then call the journey repo to find one journey in database]
     *  @param  request [id of the journey to find]
     *  @return         [a promise with the JourneyModel found]
     */
    public async retrive_one(
      request: string
    ): Promise<JourneyModel> {
  
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }
      return getOneJourney(request)
    }
  
    /**
     *  [check if the request is valid, then call the journey repo to retrive all journeys of a theseus from database]
     *  @param  request [theseus id to find]
     *  @return         [a promise with an array of JourneyModel]
     */
    public async retrieve_all_from_theseus(
      request: string
    ): Promise<JourneyModel[]> {
  
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }
      return getAllJourneysFromTheseus(request)
    }

    /**
     *  [check the type of the request, then call the journey repo to update one journey in database]
     *  @param  request [id to find for update]
     *  @param  body    [format the sent body to a JourneySchema]
     *  @return         [a promise with the JourneyModel updated]
     */
    public async update_one(
      request: string,
      body: JourneyRequest
    ): Promise<JourneyModel> {
      
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }
      return updateOneJourney(
        request,
        GenerateSchema(body),
      )
    }

    /**
     *  [check the type of the request, then call the journey repo to update one journey in database]
     *  @param  request [id to find for update]
     *  @param  body    [format the sent body to a JourneySchema]
     *  @return         [a promise with the JourneyModel updated]
     */
     public async update_all_from_theseus(
      request: string,
      body: JourneyRequest
    ): Promise<JourneyModel[]> {
      
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }
      return updateAllJourneyFromThesus(
        request,
        GenerateSchema(body),
      )
    }

    /**
     *  [check the type of the request, then call the journey repo to delete one journey in database]
     *  @param  request [id of the journey to find for deletion]
     *  @return         [a promise with the DeletedResult]
     */
    public async delete_one(
      request: string
    ): Promise<DeleteResult> {
      
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }
      return deleteOneJourney(request)
    }

    /**
     *  [check the type of the request, then call the journey repo to delete all journeys of a theseus]
     *  @param  request [id of the theseus to find for deletion]
     *  @return         [a promise with the DeletedResult]
     */
    public async delete_all_of_theseus(
      request: string
    ): Promise<DeleteResult> {
      
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }
      return deleteAllJourneyOfTheseus(request)
    }

    /**
     *  [check if the request is valid, then call the journey repo to find one journey with a pair of Theseus and Ardiane ids]
     *  @param ardianeId [id of the ardiane to find]
     *  @param theseusId [id of the theseus to find]
     *  @returns         [a promise with the JourneyModel]
     */
    public async get_pair(
      ardianeId: string,
      theseusId: string
    ): Promise<JourneyModel> {

      const verification = new StringVerification()
      if(!verification.verifyIdRequest(ardianeId))
      {
        throw new BadIdRequest(ardianeId) as Error
      } 
      else if(!verification.verifyIdRequest(theseusId))
      {
        throw new BadIdRequest(theseusId) as Error
      }
      return getPairOfTheseusAndArdiane(ardianeId, theseusId)
    }
  }
  