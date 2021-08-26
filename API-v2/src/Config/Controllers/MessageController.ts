import { MessageModel } from "../Models"
import {
  createMessage,
  getAllMessageFromReceiver,
  getAllMessageFromSender,
  getAllMessages,
  getOneMessage,
  MessageSchema
} from "../Repositories/MessageRepo"
import { StringVerification } from "../Security"

function BadIdRequest(id: string) {
  this.name = "QueryFailedError"
  this.message = "Invalid ID request : " + id + " -> Must be a number."
}

export class MessageController {

  /**
   *  [call the message repo to retrive all messages from database]
   *  @return         [a promise with an array of MessageModel]
   */
  public async retrive_all(): Promise<MessageModel[]> {
    return getAllMessages()
  }

  /**
   *  [call the message repo to create a brand new message]
   *  @param  body    [format the sent body to a MessageSchema]
   *  @return         [a promise with the MessageSchema created]
   */
  public async create(body: MessageSchema): Promise<MessageModel> {
    return createMessage(body)
  }

  /**
   *  [check if the request is valid, then call the message repo to find one message in database]
   *  @param  request [id or username to find]
   *  @return         [a promise with the UserModel found, or null]
   */
  public async retrive_one(
    request: string
  ): Promise<MessageModel> {

    const verification = new StringVerification()
    if(!verification.verifyIdRequest(request))
    {
      throw new BadIdRequest(request) as Error
    }
    return getOneMessage(request)
  }

  /**
   *  [check if the request is valid, then call the message repo to retrive all messages of a receiver from database]
   *  @param  request [receiver id to find]
   *  @return         [a promise with an array of MessageModel]
   */
  public async retrieve_all_from_receiver(
    request: string
  ): Promise<MessageModel[]> {

    const verification = new StringVerification()
    if(!verification.verifyIdRequest(request))
    {
      throw new BadIdRequest(request) as Error
    }
    return getAllMessageFromReceiver(request)
  }

  /**
   *  [check if the request is valid, then call the message repo to retrive all messages of a sender from database]
   *  @param  request [sender id to find]
   *  @return         [a promise with an array of MessageModel]
   */
  public async retrieve_all_from_sender(
    request: string
  ): Promise<MessageModel[]> {

    const verification = new StringVerification()
    if(!verification.verifyIdRequest(request))
    {
      throw new BadIdRequest(request) as Error
    }
    return getAllMessageFromSender(request)
  }
}
