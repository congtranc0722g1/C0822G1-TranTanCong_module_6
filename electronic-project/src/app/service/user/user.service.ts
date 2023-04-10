import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../../model/user/user";
import {Observable} from "rxjs";
import {TokenService} from "../login/token.service";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient, private tokenService:TokenService) { }

  updateUser(user: User){
    return this.httpClient.put("http://localhost:8080/user/update", user)
  }

  updateAvatarUser(avatar: string, userId: number){
    return this.httpClient.put("http://localhost:8080/user/update-avatar", {avatar: avatar, userId: userId})
  }

  changePassword(obj):Observable<any> {
    return this.httpClient.post('http://localhost:8080/api/auth/change-password',{username: this.tokenService.getUsername(),password: obj.password,newPassword:obj.newPassword,confirmPassword:obj.confirmPassword})
  }
}
