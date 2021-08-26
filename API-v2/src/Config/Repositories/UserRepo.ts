import { getRepository } from "typeorm"
import { UserModel } from "../Models"

function UserNotFound(user) {
  this.name = "NotFoundError"
  this.message = "User " + user + " not found."
}

/**
 *  [payload definition for POST and PUT methods]
 *  @return [nothing]
 */
export interface UserSchema {
  username: string
  firstName: string
  lastName: string
  email: string
  phone: string
}

/**
 *  [async function to retrieve all users]
 *  @return [a promise with an array of UserModel]
 */
export const getAllUsers = async (): Promise<UserModel[]> => {
  const userRepository = getRepository(UserModel)
  return userRepository.find()
}

/**
 *  [async function to create a new user]
 *  @param  request [UserSchema with data]
 *  @return         [a promise with the created user]
 */
export const createUser = async (
  request: UserSchema
): Promise<UserModel> => {

  const userRepository = getRepository(UserModel)
  const user = new UserModel()
  
  return userRepository.save({
    ...user,
    ...request,
  })
}

/**
 *  [async function to retrieve one user]
 *  @param  request     [id or username of the user to find]
 *  @param  isIdRequest [boolean to check if the request is an id]
 *  @return             [a promise with an UserModel, or null]
 */
export const getOneUser = async (
  request: string,
  isIdRequest: boolean
): Promise<UserModel> => {

  const userRepository = getRepository(UserModel)
  let user = null

  if (isIdRequest)
  {
    user = await userRepository.findOne({ id: Number(request) })
  }
  else
  {
    user = await userRepository.findOne({ username: request })
  }
    
  if (!user)
  {
    throw new UserNotFound(request) as Error
  }
  return user
}

/**
 *  [async function to update one user]
 *  @param  request     [id or username of the user to update]
 *  @param  data        [description]
 *  @param  isIdRequest [boolean to check if the request is an id]
 *  @return             [description]
 */
export const updateOneUser = async (
  request: string,
  data: UserSchema,
  isIdRequest: boolean
): Promise<UserModel> => {

  const userRepository = getRepository(UserModel)
  let user = null

  if (isIdRequest)
  {
    user = await userRepository.findOne({ id: Number(request) })
  }
  else
  {
    user = await userRepository.findOne({ username: request })
  }
    
  if (!user)
  {
    throw new UserNotFound(request) as Error
  }

  userRepository.merge(user, data)
  return userRepository.save(user)
}

/**
 *  [async function to delete one user]
 *  @param  request     [id or username of the user to delete]
 *  @param  isIdRequest [boolean to check if the request is an id]
 *  @return             [UserModel with affected user]
 */
export const deleteOneUser = async (
  request: string,
  isIdRequest: boolean
): Promise<UserModel> => {

  const userRepository = getRepository(UserModel)
  let result = null

  if (isIdRequest)
  {
    result = await userRepository.delete({ id: Number(request) })
  }
  else
  {
    result = await userRepository.delete({ username: request })
  }

  if (result.affected < 1)
  {
    throw new UserNotFound(request) as Error
  }
  return result
}
