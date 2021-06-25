import { getRepository, DeleteResult } from "typeorm"
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

// GET User by id
export const getUserById = async (id: number): Promise<User | null> => {
  const userRepository = getRepository(User)
  const user = await userRepository.findOne({ id: id })
  if (!user) {
    return null
  }
  return user
}

// GET User by username
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

export const updateUserById = async (id: number, request: UserSchema): Promise<User | null> => {
  const userRepository = getRepository(User)
  const user = await userRepository.findOne({ id: id })
  if (!user) {
    return null
  }
  userRepository.merge(user, request)
  return userRepository.save(user)
}

export const updateUserByUsername = async (username: string, request: UserSchema): Promise<User | null> => {
  const userRepository = getRepository(User)
  const user = await userRepository.findOne({ username: username })
  if (!user) {
    return null
  }
  userRepository.merge(user, request)
  return userRepository.save(user)
}

export const deleteUserById = async (id: number): Promise<DeleteResult | null> => {
  const userRepository = getRepository(User)
  const user = await userRepository.delete({ id: id })
  if (user.affected < 1) {
    return null
  }
  return user
}

export const deleteUserByUsername = async (username: string): Promise<DeleteResult | null> => {
  const userRepository = getRepository(User)
  const user = await userRepository.delete({ username: username })
  if (user.affected < 1) {
    return null
  }
  return user
}
