import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartRoutingModule } from './cart-routing.module';
import { CartComponent } from './cart/cart.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { PaymentComponent } from './payment/payment.component';


@NgModule({
  declarations: [CartComponent, PaymentComponent],
  imports: [
    CommonModule,
    CartRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class CartModule { }
