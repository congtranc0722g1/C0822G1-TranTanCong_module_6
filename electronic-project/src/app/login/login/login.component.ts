import {Component, OnInit} from '@angular/core';

import {Router} from "@angular/router";
// @ts-ignore
import {Title} from "@angular/platform-browser";
import {FormControl, FormGroup} from "@angular/forms";
import {LoginService} from "../../service/login/login.service";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";
import {ToastrService} from "ngx-toastr";


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
  confirmPasswordError: '';
  form = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
    rememberMe: new FormControl(true)
  })
  registerForm = new FormGroup({
    username: new FormControl(),
    email: new FormControl(),
    password: new FormControl(),
    confirmPassword: new FormControl(),
    roles: new FormControl(['customer'])
  });
  islogged = false;

  constructor(private title: Title,
              private loginService: LoginService,
              private token: TokenService,
              private router: Router,
              private share: ShareService,
              private toastrService: ToastrService,
              private titleService: Title) {

    this.titleService.setTitle("Đăng nhập");
  }

  ngOnInit(): void {
    window.scroll(0,50)
    this.title.setTitle('Trang Đăng Nhập');
    this.islogged = this.token.isLogger();
    if (this.islogged) {
      this.router.navigateByUrl('/')
    }

  }

  login() {
    this.message = ''
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
        if (error.error.message) {
          this.message = error.error.message
        } else {
          for (let i = 0; i < error.error.length; i++) {
            this.message = error.error[i].defaultMessage;
          }
        }
      }
    )
  }

  register() {
    this.usernameError = '';
    this.emailError = '';
    this.passwordError = '';
    this.confirmPasswordError = '';
    console.log(this.registerForm.value)
    this.loginService.register(this.registerForm.value).subscribe(next => {
      this.toastrService.success("Đăng ký thành công", "Thông báo")
    }, error => {
      console.log(error)
      this.toastrService.warning("Đăng ký thất bại", "Thông báo")
      for (let i = 0; i < error.error.length; i++) {
        if (error.error[i].field == 'username') {
          this.usernameError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'email') {
          this.emailError = error.error[i].defaultMessage;
        } else if (error.error[i].field == 'password') {
          this.passwordError = error.error[i].defaultMessage;
        }else if (error.error[i].field == 'confirmPassword') {
          this.confirmPasswordError = error.error[i].defaultMessage;
        }
      }
    })
  }
}
