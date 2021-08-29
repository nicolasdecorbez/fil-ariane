import {
    Entity,
    Column,
    CreateDateColumn,
    PrimaryGeneratedColumn,
    UpdateDateColumn,
    Unique
  } from "typeorm"
  
  /**
   *  [Entity to describe Friends in database]
   */
  @Entity("friends")
  @Unique(["firstId", "secondId"])
  export class FriendsModel {
    @PrimaryGeneratedColumn()
    id!: number
  
    @Column({
      type: "int"
    })
    firstId!: number
  
    @Column({
      type: "int"
    })
    secondId!: number
  
    @CreateDateColumn()
    createdAt!: Date
  
    @UpdateDateColumn()
    updatedAt!: Date
  }