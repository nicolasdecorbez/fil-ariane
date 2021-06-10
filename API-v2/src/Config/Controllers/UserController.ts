import { User } from "../Models"
import {
  UserSchema,
  getAllUsers,
  getUserById,
  createUser
} from "../Repositories/User"

export default class UserController {
  // GET All Users
  public async getAllUsers(): Promise<Array<User>> {
    return getAllUsers()
  }

  // GET User by id
  public async getUserById(id: string): Promise<User | null> {
    // check if 'id' is only digits.
    const reg = new RegExp("/^[0-9]*$/")

    if (reg.test(id)) {
      return getUserById(Number(id))
    } else {
      return null
    }
  }

  // POST new User
  public async createUser(body: UserSchema): Promise<User> {
    return createUser(body)
  }
}
