import { getRepository } from "typeorm"
import { User } from "../Models"

// Payload definition for POST and PUT methods.
export interface UserSchema {
  username: string
  firstName: string
  lastName: string
  email: string
  phone: string
}

// GET All Users
export const getAllUsers = async (): Promise<Array<User>> => {
  const userRepository = getRepository(User)
  return userRepository.find()
}

// POST new User
export const createUser = async (request: UserSchema): Promise<User> => {
  const userRepository = getRepository(User)
  const user = new User()
  return userRepository.save({
    ...user,
    ...request,
  })
}

export const getOneUser = async (
  request: string,
  isIdRequest: boolean
): Promise<User | null> => {

  const userRepository = getRepository(User)
  let user = null
  if (isIdRequest)
    user = await userRepository.findOne({ id: Number(request) })
  else
    user = await userRepository.findOne({ username: request })
  return user
}


export const updateOneUser = async (
  request: string,
  data: UserSchema,
  isIdRequest: boolean
): Promise<User | null> => {

  const userRepository = getRepository(User)
  let user = null
  if (isIdRequest)
    user = await userRepository.findOne({ id: Number(request) })
  else
    user = await userRepository.findOne({ username: request })

  if (!user)
    return null

  userRepository.merge(user, data)
  return userRepository.save(user)
}


export const deleteOneUser = async (
  request: string,
  isIdRequest: boolean
): Promise<User | null> => {

  const userRepository = getRepository(User)
  let user = null
  if (isIdRequest)
    user = await userRepository.delete({ id: Number(request) })
  else
    user = await userRepository.delete({ username: request })

  if (user.affected < 1)
    return null

  return user
}
