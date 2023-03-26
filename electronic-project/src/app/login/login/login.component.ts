import {Component, OnInit} from '@angular/core';

import {Router} from "@angular/router";
// @ts-ignore
import {Title} from "@angular/platform-browser";
import {FormControl, FormGroup} from "@angular/forms";
import {LoginService} from "../../service/login/login.service";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";


// @ts-ignore
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  name = 'Thông tin cá nhân';
  message = ''
  nameError = '';
  usernameError = '';
  emailError = '';
  passwordError = '';
  form = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
    rememberMe: new FormControl(true)
  })
  registerForm = new FormGroup({
    username: new FormControl(),
    name: new FormControl(),
    email: new FormControl(),
    password: new FormControl(),
    roles: new FormControl('customer')
  });
  islogged = false;
  constructor(private title:Title,private loginService: LoginService, private token: TokenService, private router: Router, private share: ShareService) {
  }

  ngOnInit(): void {
    window.scroll(0,200)
    this.title.setTitle('Trang Đăng Nhập');
    this.islogged = this.token.isLogger();
    if (this.islogged) {
      this.router.navigateByUrl('/')
    }

  }

  login() {
    this.loginService.login(this.form.value).subscribe(next => {
        if (this.form.controls.rememberMe.value) {
          this.token.rememberMe(next.token, next.id, next.name, next.username, next.phoneNumber, next.email, next.address, next.age,
            next.gender, next.dateOfBirth, next.avatar, next.roles, 'local');

        } else {
          this.token.rememberMe(next.token, next.id, next.name, next.username, next.phoneNumber, next.email, next.address, next.age,
            next.gender, next.dateOfBirth, next.avatar, next.roles, 'session');
        }
      this.token.setIsLoggedIn(next.name);
        this.share.sendClickEvent();
        // location.href='http://localhost:4200/'
      this.router.navigateByUrl('/')
      }, error => {
        this.message = error.error.message
      }
    )
  }

  register() {
    this.nameError = '';
    this.usernameError = '';
    this.emailError = '';
    this.passwordError = '';
    this.loginService.register(this.registerForm.value).subscribe(next => {
      document.getElementById('dismiss').click();
      alert("Đăng ký thành công")
    }, error => {
      console.log(error)
      alert("Đăng ký thất bại")
      for (let i = 0; i < error.error.length; i++) {
        if (error.error[i].field == 'name') {
          this.nameError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'username') {
          this.usernameError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'email') {
          this.emailError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'password') {
          this.passwordError = error.error[i].defaultMessage;
        }
      }
    })
  }
}
