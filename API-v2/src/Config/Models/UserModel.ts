import {
  Entity,
  Column,
  CreateDateColumn,
  PrimaryGeneratedColumn,
  UpdateDateColumn
} from "typeorm"


/**
 *  [Entity to describe Users in database]
 */
@Entity("users")
export class UserModel {
  @PrimaryGeneratedColumn()
  id!: number

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
      type: "varchar"
  })
  password!: string

  @Column({
      type: "varchar",
      length: "100",
      unique: true,
  })
  email!: string

  @Column({
      type: "varchar",
      unique: true,
  })
  phone!: string

  @CreateDateColumn()
  createdAt!: Date

  @UpdateDateColumn()
  updatedAt!: Date
}
