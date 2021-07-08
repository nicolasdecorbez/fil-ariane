import { DeleteResult } from "typeorm"
import { User } from "../Models"
import {
  createUser,
  deleteOneUser,
  getOneUser,
  getAllUsers,
  updateOneUser,
  UserSchema
} from "../Repositories/User"
import { StringVerification } from "../Security"

export default class UserController {
  // GET All Users
  public async getAllUsers(): Promise<Array<User>> {
    return getAllUsers()
  }

  // POST new User
  public async createUser(body: UserSchema): Promise<User> {
    return createUser(body)
  }

  // GET One User
  public async getOneUser(request: string): Promise<User | null> {
    const verification = new StringVerification()
    return getOneUser(request, verification.verifyIdRequest(request))
  }

  // UPDATE One User
  public async updateOneUser(request: string, body: UserSchema): Promise<User | null> {
    const verification = new StringVerification()
    return updateOneUser(request, body, verification.verifyIdRequest(request))
  }

  public async deleteOneUser(request: string): Promise<User | null> {
    const verification = new StringVerification()
    return deleteOneUser(request, verification.verifyIdRequest(request))
  }
}
