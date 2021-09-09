import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

export interface UsersInterface {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  password: string;
};

export const URL = "http://api.ardiane.com/users";

@Injectable()
export class DatabaseService {

  constructor(private httpClient: HttpClient) { }

  users: UsersInterface[] = new Array();
  status: number = 500;

  getAllUsers(): void {
    this.httpClient
    .get<UsersInterface[]>(URL)
    .subscribe(
      (response: UsersInterface[]) => {
        this.users = [ ...response ];
      },
      (error) => {
        console.error("ERROR: ", error);
      }
    )
  }

  saveUser(input: UsersInterface): Observable<UsersInterface> {
    return this.httpClient.post<UsersInterface>(URL, input)
  }

  health_check() {
    this.httpClient
    .get<any>(URL, {observe: "response"})
    .subscribe(
      (response) => {
        this.status = response.status
      },
      (error) => {
        console.error("ERROR: ", error);
      }
    );
  }
}