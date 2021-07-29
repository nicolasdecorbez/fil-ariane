import {
  Entity,
  Column,
  CreateDateColumn,
  PrimaryGeneratedColumn,
  UpdateDateColumn
} from "typeorm"

@Entity()
export class User {
  @PrimaryGeneratedColumn()
  id!: number

  @Column({
      type: "varchar",
      length: "100",
      unique: true
  })
  username!:string

  @Column({
      type: "varchar",
      length: "50"
  })
  firstName!: string

  @Column({
      type: "varchar",
      length: "50"
  })
  lastName!: string

  @Column({
      type: "varchar",
      length: "100",
      unique: true,
  })
  email!: string

  @Column({
      type: "varchar",
      length: "10",
      unique: true,
  })
  phone!: string

  @CreateDateColumn()
  createdAt!: Date

  @UpdateDateColumn()
  updatedAt!: Date
}
