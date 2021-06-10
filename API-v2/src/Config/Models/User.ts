import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
} from "typeorm"

@Entity()
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
