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
    updateAllJourneyFromThesus
  } from "../Repositories/JourneyRepo"
import { StringVerification } from "../Security"

function BadIdRequest(id: string) {
    this.name = "QueryFailedError"
    this.journey = "Invalid ID request : " + id + " -> Must be a number."
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
    public async create(body: JourneySchema): Promise<JourneyModel> {
      return createJourney(body)
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
      body: JourneySchema
    ): Promise<JourneyModel> {
      
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }
      return updateOneJourney(
        request,
        body,
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
      body: JourneySchema
    ): Promise<JourneyModel[]> {
      
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }

      return updateAllJourneyFromThesus(
        request,
        body,
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
  }
  