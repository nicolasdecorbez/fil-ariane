import {
  Entity,
  Column,
  CreateDateColumn,
  PrimaryGeneratedColumn,
  Unique,
  UpdateDateColumn
} from "typeorm"

@Entity()
@Unique(["username", "email", "phone"])
export class User {
  @PrimaryGeneratedColumn()
  id!: number

  @Column()
  username!:string

  @Column()
  firstName!: string

  @Column()
  lastName!: string

  @Column()
  email!: string

  @Column()
  phone!: string

  @CreateDateColumn()
  createdAt!: Date

  @UpdateDateColumn()
  updatedAt!: Date
}
