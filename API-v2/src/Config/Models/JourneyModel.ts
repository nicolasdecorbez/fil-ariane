import {
    Entity,
    Column,
    CreateDateColumn,
    PrimaryGeneratedColumn,
    UpdateDateColumn
  } from "typeorm"
  
  /**
   *  [Entity to describe Journey in database]
   */
  @Entity("journey")
  export class JourneyModel {
    @PrimaryGeneratedColumn()
    id!: number
  
    @Column({
      type: "int"
    })
    ardianeId!: number
  
    @Column({
      type: "int"
    })
    theseusId!: number
  
    @Column({
      type: "text"
    })
    location!: string

    @Column({
    type: "date"
    })
    returnDate!: string
  
    @CreateDateColumn()
    createdAt!: Date
  
    @UpdateDateColumn()
    updatedAt!: Date
  }
  