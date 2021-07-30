import { MessageModel } from "../Models"
import {
  createMessage,
  getAllMessages,
  MessageSchema
} from "../Repositories/MessageRepo"

export class MessageController {
  // GET All Users
  public async retrive_all(): Promise<Array<MessageModel>> {
    return getAllMessages()
  }

  // POST new User
  public async create(body: MessageSchema): Promise<MessageModel> {
    return createMessage(body)
  }
}
