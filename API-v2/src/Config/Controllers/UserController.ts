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

  /**
   *  [call the user repo to retrive all users from database]
   *  @return         [a promise with an array of UserModel]
   */
  public async retrive_all(): Promise<UserModel[]> {
    return getAllUsers()
  }

  /**
   *  [call the user repo to create a brand new user]
   *  @param  body    [format the sent body to a UserSchema]
   *  @return         [a promise with the UserModel created]
   */
  public async create(
    body: UserSchema
  ): Promise<UserModel> {
    return createUser(body)
  }

  /**
   *  [check the type of the request, then call the user repo to find one user in database]
   *  @param  request [id or username to find]
   *  @return         [a promise with the UserModel found, or null]
   */
  public async retrive_one(
    request: string
  ): Promise<UserModel> {
    const verification = new StringVerification()
    return getOneUser(
      request,
      verification.verifyIdRequest(request)
    )
  }

  /**
   *  [check the type of the request, then call the user repo to update one user in database]
   *  @param  request [id or username to find for update]
   *  @param  body    [format the sent body to a UserSchema]
   *  @return         [a promise with the UserModel updated, or null]
   */
  public async update_one(
    request: string,
    body: UserSchema
  ): Promise<UserModel> {
    const verification = new StringVerification()
    return updateOneUser(
      request,
      body,
      verification.verifyIdRequest(request)
    )
  }

  /**
   *  [check the type of the request, then call the user repo to delete one user in database]
   *  @param  request [id or username to find for deletion]
   *  @return         [a promise with the UserModel deleted, or null]
   */
  public async delete_one(
    request: string
  ): Promise<UserModel> {
    const verification = new StringVerification()
    return deleteOneUser(
      request,
      verification.verifyIdRequest(request)
    )
  }
}
