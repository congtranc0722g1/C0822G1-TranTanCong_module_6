import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CartComponent} from "./cart/cart.component";
import {PaymentComponent} from "./payment/payment.component";
import {PurchaseHistoryComponent} from "./purchase-history/purchase-history.component";
import {CustomerGuard} from "../login/security/customer.guard";


const routes: Routes = [
  {path:'', canActivate: [CustomerGuard], component: CartComponent},
  {path: 'payment', canActivate: [CustomerGuard], component: PaymentComponent},
  {path: 'purchase-history', canActivate: [CustomerGuard], component: PurchaseHistoryComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartRoutingModule { }
