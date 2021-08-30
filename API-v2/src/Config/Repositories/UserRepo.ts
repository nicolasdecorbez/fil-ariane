import { DeleteResult, getRepository } from "typeorm"
import { UserModel } from "../Models"

/**
 * [generate an error message if the user doesn't exists]
 * @param user      [the id/username of the user]
 */
function UserNotFound(user) {
  this.name = "NotFoundError"
  this.message = "User " + user + " not found."
}

/**
 *  [payload definition for POST and PUT methods]
 *  @return [nothing]
 */
export interface UserSchema {
  firstName: string
  lastName: string
  password: string
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
 *  @param  request     [id of the user to find]
 *  @return             [a promise with an UserModel]
 */
export const getOneUser = async (
  request: string
): Promise<UserModel> => {

  const userRepository = getRepository(UserModel)
  let user = null
  user = await userRepository.findOne({ id: Number(request) })
    
  if (!user)
  {
    throw new UserNotFound(request) as Error
  }
  return user
}

/**
 *  [async function to retrieve one user by its phone number]
 *  @param  request     [phone number of the user to find]
 *  @return             [a promise with an UserModel]
 */
 export const getOneUserByPhone = async (
  request: string
): Promise<UserModel> => {

  const userRepository = getRepository(UserModel)
  let user = null
  user = await userRepository.findOne({ phone: request })
    
  if (!user)
  {
    throw new UserNotFound(
      "phone number `" + request
    ) as Error
  }
  return user
}

/**
 *  [async function to update one user]
 *  @param  request     [id of the user to update]
 *  @param  data        [description]
 *  @return             [description]
 */
export const updateOneUser = async (
  request: string,
  data: UserSchema
): Promise<UserModel> => {

  const userRepository = getRepository(UserModel)
  let user = null
  user = await userRepository.findOne({ id: Number(request) })
    
  if (!user)
  {
    throw new UserNotFound(request) as Error
  }

  userRepository.merge(user, data)
  return userRepository.save(user)
}

/**
 *  [async function to delete one user]
 *  @param  request     [id of the user to delete]
 *  @return             [DeleteResult with affected user]
 */
export const deleteOneUser = async (
  request: string
): Promise<DeleteResult> => {

  const userRepository = getRepository(UserModel)
  let result = null
  result = await userRepository.delete({ id: Number(request) })

  if (result.affected < 1)
  {
    throw new UserNotFound(request) as Error
  }
  return result
}
