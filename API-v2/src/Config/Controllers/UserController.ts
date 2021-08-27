import { DeleteResult } from "typeorm"
import { UserModel } from "../Models"
import {
  createUser,
  deleteOneUser,
  getOneUser,
  getAllUsers,
  updateOneUser,
  UserSchema,
  getOneUserByPhone
} from "../Repositories/UserRepo"
import { StringVerification } from "../Security"

/**
 * [generate an error message when the id is not an int]
 * @param id    [the string with the invalid id]
 */
 function BadIdRequest(id: string) {
  this.name = "QueryFailedError"
  this.message = "Invalid ID request : " + id + " -> Must be a number."
}

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
   *  [check if the request is valid, then call the user repo to find one user in database]
   *  @param  request [id to find]
   *  @return         [a promise with the UserModel found, or null]
   */
  public async retrive_one(
    request: string
  ): Promise<UserModel> {
    const verification = new StringVerification()
    if(!verification.verifyIdRequest(request))
    {
      throw new BadIdRequest(request) as Error
    }
    
    return getOneUser(
      request
    )
  }

  /**
   *  [check if the request is valid, then call the user repo to find one user in database]
   *  @param  request [id to find]
   *  @return         [a promise with the UserModel found, or null]
   */
  public async retrive_one_by_phone_number(
    request: string
  ): Promise<UserModel> {
    const verification = new StringVerification()
    if(!verification.verifyIdRequest(request))
    {
      throw new BadIdRequest(request) as Error
    }
    
    return getOneUserByPhone(
      request
    )
  }

  /**
   *  [check if the request is valid, then call the user repo to update one user in database]
   *  @param  request [id to find for update]
   *  @param  body    [format the sent body to a UserSchema]
   *  @return         [a promise with the UserModel updated, or null]
   */
  public async update_one(
    request: string,
    body: UserSchema
  ): Promise<UserModel> {
    const verification = new StringVerification()
    if(!verification.verifyIdRequest(request))
    {
      throw new BadIdRequest(request) as Error
    }

    return updateOneUser(
      request,
      body
    )
  }

  /**
   *  [check if the request is valid, then call the user repo to delete one user in database]
   *  @param  request [id to find for deletion]
   *  @return         [a promise with the UserModel deleted, or null]
   */
  public async delete_one(
    request: string
  ): Promise<DeleteResult> {
    const verification = new StringVerification()
    if(!verification.verifyIdRequest(request))
    {
      throw new BadIdRequest(request) as Error
    }

    return deleteOneUser(
      request
    )
  }
}
