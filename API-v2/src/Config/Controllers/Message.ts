import { Message } from "../Models"
import {
  createMessage,
  getAllMessages,
  MessageSchema
} from "../Repositories/Message"

export class MessageController {
  // GET All Users
  public async getAllMessages(): Promise<Array<Message>> {
    return getAllMessages()
  }

  // POST new User
  public async createMessage(body: MessageSchema): Promise<Message> {
    return createMessage(body)
  }
}
