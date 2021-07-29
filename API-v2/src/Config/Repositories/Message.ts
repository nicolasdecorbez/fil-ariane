import { getRepository } from "typeorm"
import { Message } from "../Models"

// Payload definition for POST and PUT methods.
export interface MessageSchema {
  senderId: number
  receiverId: number
  messageContent: string
}

// GET All messages
export const getAllMessages = async (): Promise<Array<Message>> => {
  const messageRepository = getRepository(Message)
  return messageRepository.find()
}

// POST new message
export const createMessage = async (request: MessageSchema): Promise<Message> => {
  const messageRepository = getRepository(Message)
  const message = new Message()
  return messageRepository.save({
    ...message,
    ...request,
  })
}
