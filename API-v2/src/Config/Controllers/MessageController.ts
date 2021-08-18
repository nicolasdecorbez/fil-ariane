import { MessageModel } from "../Models"
import {
  createMessage,
  getAllMessages,
  MessageSchema
} from "../Repositories/MessageRepo"

export class MessageController {

  /**
   *  [call the message repo to retrive all messages from database]
   *  @return         [a promise with an array of MessageModel]
   */
  public async retrive_all(): Promise<Array<MessageModel>> {
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
}
