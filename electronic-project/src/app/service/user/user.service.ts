import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../model/user/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  updateUser(user: User){
    return this.httpClient.put("http://localhost:8080/user/update", user)
  }

  updateAvatarUser(avatar: string, userId: number){
    return this.httpClient.put("http://localhost:8080/user/update-avatar", {avatar: avatar, userId: userId})
  }
}
