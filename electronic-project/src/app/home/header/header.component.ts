import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../service/login/token.service";
import {User} from "../../model/user/user";
import {LoginService} from "../../service/login/login.service";
import {ShareService} from "../../service/login/share.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user:User
  isLogged = false;
  close = true;
  name: '';
  constructor(private router:Router,private token:TokenService,private loginService:LoginService,private share:ShareService) {
    this.token.getUserNameLoggedIn().subscribe(next => {this.name = next
      this.isLogged = this.token.isLogger();
      this.loader();
      this.share.getClickEvent().subscribe(next => {
        this.loader()
      })
    });
  }

  ngOnInit(): void {

    this.isLogged = this.token.isLogger();

    this.share.getClickEvent().subscribe(
      next => {
        this.loader()
      }
    )
      this.loader();
      this.share.getClickEvent().subscribe(
        next => {
          this.loader()
        }
      )
  }
  logout() {
    this.close = false;
    this.isLogged = false;
    this.token.logout()
    this.share.sendClickEvent()

  }
  loader() {
    if (this.isLogged) {
      this.loginService.profile(this.token.getId()).subscribe(
        next => {
          this.user = next;
          console.log(next)
        }
      )
    }
  }
}
