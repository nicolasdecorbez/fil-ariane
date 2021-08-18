import { getRepository } from "typeorm"
import { MessageModel } from "../Models"

/**
 *  payload definition for POST and PUT methods
 */
export interface MessageSchema {
  senderId: number
  receiverId: number
  messageContent: string
}

/**
 *  [async function to retrieve all messages]
 *  @return [a promise with an array of MessageModel]
 */
export const getAllMessages = async (): Promise<Array<MessageModel>> => {
  const messageRepository = getRepository(MessageModel)
  return messageRepository.find()
}

/**
 *  [async function to create a new message]
 *  @param  request [MessageSchema with data]
 *  @return         [a promise with the created message]
 */
export const createMessage = async (request: MessageSchema): Promise<MessageModel> => {
  const messageRepository = getRepository(MessageModel)
  const message = new MessageModel()
  return messageRepository.save({
    ...message,
    ...request,
  })
}
