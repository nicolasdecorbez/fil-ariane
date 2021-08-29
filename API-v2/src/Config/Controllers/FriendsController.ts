import { FriendsModel } from "../Models"
import {
    createFriends,
    getAllFriendsIds,
    FriendsSchema
} from "../Repositories/FriendsRepo"
import { StringVerification } from "../Security"


/**
 * [generate an error message when the id is not an int]
 * @param id    [the string with the invalid id]
 */
function BadIdRequest(id: string) {
    this.name = "QueryFailedError"
    this.journey = "Invalid ID request : " + id + " -> Must be a number."
}

export class FriendsController {

    /**
     *  [call the friends repo to create a brand new friend]
     *  @param  body    [format the sent body to a FriendsSchema]
     *  @return         [a promise with the FriendsModel created]
     */
    public async create(body: FriendsSchema): Promise<FriendsModel> {
      return createFriends(body)
    }

    /**
     *  [call the friends repo to to retrieve a friend list]
     *  @param  request [the id of the user to find]
     *  @return         [a promise with the an array of ids]
     */
    public async retrieve_all_friends(request: string): Promise<Number[]> {
      
      const verification = new StringVerification()
      if(!verification.verifyIdRequest(request))
      {
        throw new BadIdRequest(request) as Error
      }
      return getAllFriendsIds(request)
    }
}

