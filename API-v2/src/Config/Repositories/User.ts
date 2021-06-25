import { getRepository } from "typeorm"
import { User } from "../Models"

// Payload definition for POST and PUT methods.
export interface UserSchema {
  firstName: string
  lastName: string
  email: string
}

// GET All Users
export const getAllUsers = async (): Promise<Array<User>> => {
  const userRepository = getRepository(User)
  return userRepository.find()
}

// GET User by id
export const getUserById = async (id: number): Promise<User | null> => {
  const userRepository = getRepository(User)
  const user = await userRepository.findOne({ id: id })
  if (!user) {
    return null
  }
  return user
}

export const getUserByUsername = async (username: string): Promise<User | null> => {
  const userRepository = getRepository(User)
  const user = await userRepository.findOne({ username: username })
  if (!user) {
    return null
  }
  return user
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
