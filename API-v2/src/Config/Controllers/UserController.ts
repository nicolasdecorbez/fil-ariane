import { UserModel } from "../Models"
import {
  createUser,
  deleteOneUser,
  getOneUser,
  getAllUsers,
  updateOneUser,
  UserSchema
} from "../Repositories/UserRepo"
import { StringVerification } from "../Security"

export class UserController {
  // GET All Users
  public async retrive_all(): Promise<Array<UserModel>> {
    return getAllUsers()
  }

  // POST new User
  public async create(body: UserSchema): Promise<UserModel> {
    return createUser(body)
  }

  // GET One User
  public async retrive_one(request: string): Promise<UserModel | null> {
    const verification = new StringVerification()
    return getOneUser(request, verification.verifyIdRequest(request))
  }

  // UPDATE One User
  public async update_one(request: string, body: UserSchema): Promise<UserModel | null> {
    const verification = new StringVerification()
    return updateOneUser(request, body, verification.verifyIdRequest(request))
  }

  public async delete_one(request: string): Promise<UserModel | null> {
    const verification = new StringVerification()
    return deleteOneUser(request, verification.verifyIdRequest(request))
  }
}
