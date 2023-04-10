import { Component, OnInit } from '@angular/core';
import Swal from "sweetalert2";
import {UserService} from "../../service/user/user.service";
import {FormControl, FormGroup} from "@angular/forms";
import {User} from "../../model/user/user";
import {LoginService} from "../../service/login/login.service";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  user: User;
  isLogged = false;

  formPassword = new FormGroup({
    password: new FormControl(),
    newPassword: new FormControl(),
    confirmPassword: new FormControl()
  })

  constructor(private userService: UserService,
              private loginService:LoginService,
              private token: TokenService,
              private share: ShareService,
              private toastrService: ToastrService,
              private router: Router,
              private titleService: Title) {
    this.share.getClickEvent().subscribe(next => {
      this.loader();
    })

    this.titleService.setTitle("Đổi mật khẩu");
  }

  ngOnInit(): void {
    this.loader();
  }

  passwordError = '';
  newPasswordError = '';
  confirmPasswordError = '';

  changePassword() {
    this.passwordError = '';
    this.newPasswordError = '';
    this.confirmPasswordError = '';
    this.userService.changePassword(this.formPassword.value).subscribe(
      next => {
        this.toastrService.success("Thay đổi mật khẩu thành công", "Thông báo")
        this.router.navigateByUrl("/user/profile")
      }, error => {
        console.log(error)
        this.toastrService.warning("Thay đổi mật khẩu thất bại", "Thông báo")
        for (let i = 0; i < error.error.length; i++) {
          if (error.error[i].field == 'password') {
            this.passwordError = error.error[i].defaultMessage;
          } else if (error.error[i].field == 'newPassword') {
            this.newPasswordError = error.error[i].defaultMessage;
          } else if (error.error[i].field == 'confirmPassword') {
            this.confirmPasswordError = error.error[i].defaultMessage;
          }
        }
      }
    )
  }

  loader() {
    if (this.isLogged) {
      this.loginService.profile(this.token.getId()).subscribe(
        next => {
          this.user = next;
        }
      )
    }
  }

}
