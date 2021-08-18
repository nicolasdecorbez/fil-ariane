import {
  Entity,
  Column,
  CreateDateColumn,
  PrimaryGeneratedColumn,
  UpdateDateColumn
} from "typeorm"

/**
 *  [Entity to describe Messages in database]
 */
@Entity("messages")
export class MessageModel {
  @PrimaryGeneratedColumn()
  id!: number

  @Column({
    type: "int"
  })
  senderId!: number

  @Column({
    type: "int"
  })
  receiverId!: number

  @Column({
    type: "text"
  })
  messageContent!: string

  @CreateDateColumn()
  createdAt!: Date

  @UpdateDateColumn()
  updatedAt!: Date
}
