import { getRepository } from "typeorm"
import { MessageModel } from "../Models"

// Payload definition for POST and PUT methods.
export interface MessageSchema {
  senderId: number
  receiverId: number
  messageContent: string
}

// GET All messages
export const getAllMessages = async (): Promise<Array<MessageModel>> => {
  const messageRepository = getRepository(MessageModel)
  return messageRepository.find()
}

// POST new message
export const createMessage = async (request: MessageSchema): Promise<MessageModel> => {
  const messageRepository = getRepository(MessageModel)
  const message = new MessageModel()
  return messageRepository.save({
    ...message,
    ...request,
  })
}
