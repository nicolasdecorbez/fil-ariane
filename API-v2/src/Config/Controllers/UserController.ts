import { User } from "../Models"
import {
  UserSchema,
  getAllUsers,
  getUserById,
  getUserByUsername,
  createUser
} from "../Repositories/User"

export default class UserController {
  // GET All Users
  public async getAllUsers(): Promise<Array<User>> {
    return getAllUsers()
  }

  // GET User by id
  public async getUserById(id: string): Promise<User | null> {
    return getUserById(Number(id))
  }

  public async getUserByUsername(username: string): Promise<User | null> {
    return getUserByUsername(username)
  }

  // POST new User
  public async createUser(body: UserSchema): Promise<User> {
    return createUser(body)
  }
}
