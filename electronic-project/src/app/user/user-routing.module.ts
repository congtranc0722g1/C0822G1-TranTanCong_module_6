import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserEditComponent} from "./user-edit/user-edit.component";
import {ChangePasswordComponent} from "./change-password/change-password.component";
import {AdminGuard} from "../login/security/admin.guard";
import {CustomerGuard} from "../login/security/customer.guard";


const routes: Routes = [
  {path:'profile', canActivate: [CustomerGuard], component: UserEditComponent},
  {path:'profile/change-password', canActivate: [ CustomerGuard], component: ChangePasswordComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
