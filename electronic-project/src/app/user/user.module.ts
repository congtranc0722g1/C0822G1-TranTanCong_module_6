import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserEditComponent } from './user-edit/user-edit.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [UserEditComponent],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule
  ]
})
export class UserModule { }
