import { getRepository } from "typeorm"
import { MessageModel } from "../Models"

function ErrorNotFound(type: string, id: string) {
  this.name = "NotFoundError"
  this.message = type + " " + id + " not found."
}

/**
 *  payload definition for POST
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
export const createMessage = async (
  request: MessageSchema
): Promise<MessageModel> => {

  const messageRepository = getRepository(MessageModel)
  const message = new MessageModel()

  return messageRepository.save({
    ...message,
    ...request,
  })
}

/**
 *  [async function to retrieve one message]
 *  @param  request     [id of the message to find]
 *  @return             [a promise with a MessageModel]
 */
export const getOneMessage = async (
  request: string
): Promise<MessageModel> => {
  
  const messageRepository = getRepository(MessageModel)
  let message = null
  message = await messageRepository.findOne({id: Number(request)})

  if(!message)
    throw new ErrorNotFound("Message", request) as Error

  return message
}

/**
 *  [async function to retrieve all messages from a receiver]
 *  @param  request     [id of the receiver to find]
 *  @return             [a promise with an array of MessageModel]
 */
export const getAllMessageFromReceiver = async (
  request: string
): Promise<Array<MessageModel>> => {
  
  const messageRepository = getRepository(MessageModel)
  const message = await messageRepository.find({
    where: {
       receiverId: Number(request)
    }
  })

  if(message.length < 1)
    throw new ErrorNotFound("Receiver", request) as Error

  return message
}

/**
 *  [async function to retrieve all messages from a sender]
 *  @param  request     [id of the sender to find]
 *  @return             [a promise with an array of MessageModel]
 */
export const getAllMessageFromSender = async (
  request: string
): Promise<Array<MessageModel>> => {
  
  const messageRepository = getRepository(MessageModel)
  const message = await messageRepository.find({
    where: { 
       senderId: Number(request) 
    }
  })

  if(message.length < 1)
    throw new ErrorNotFound("Sender", request) as Error

  return message
}
