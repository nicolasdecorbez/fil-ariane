import { getRepository } from "typeorm"
import { FriendsModel } from "../Models"


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
export interface FriendsSchema {
  firstId: number
  secondId: number
}

/**
 *  [async function to create a new friends]
 *  @param  request [FriendsSchema with data]
 *  @return         [a promise with the created friends]
 */
export const createFriends = async (
  request: FriendsSchema
): Promise<FriendsModel> => {
  
  const friendsRepository = getRepository(FriendsModel)
  const friends = new FriendsModel()
  return friendsRepository.save({
    ...friends,
    ...request,
  })
}

export const getAllFriendsIds = async (
  request: string
): Promise<number[]> => {

  const friendsIds = []
  const friendsRepository = getRepository(FriendsModel)

  const friendsList = await friendsRepository.find({
    where: [
      { firstId: Number(request) },
      { secondId: Number(request) }
    ]
  })
  
  for await (const friend of friendsList)
  {
    if (friend.firstId != Number(request) && !friendsIds.includes(friend.firstId))
    {
      friendsIds.push(friend.firstId)
    }
    else if (friend.secondId != Number(request) && !friendsIds.includes(friend.secondId))
    {
      friendsIds.push(friend.secondId)
    }
  }

  if (friendsIds.length < 1)
  {
    throw new ErrorNotFound("friends for user", request) as Error
  }
  return friendsIds
}