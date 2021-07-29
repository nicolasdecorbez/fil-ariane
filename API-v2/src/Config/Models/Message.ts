import {
  Entity,
  Column,
  CreateDateColumn,
  PrimaryGeneratedColumn,
  UpdateDateColumn
} from "typeorm"

@Entity()
export class Message {
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
