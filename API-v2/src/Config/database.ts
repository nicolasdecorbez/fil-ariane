import { ConnectionOptions } from "typeorm"
import { User } from "./Models"


const config: ConnectionOptions = {
  type: "postgres",
  host: process.env.DATABASE_HOST || "localhost",
  port: Number(process.env.DATABASE_PORT) || 5432,
  username: process.env.DATABASE_USER || "postgres",
  password: process.env.DATABASE_PASSWORD || "postgres",
  database: process.env.DATABASE_DB || "postgres",
  entities: [User],
  synchronize: true,
}

export default config
