import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CartComponent} from "./cart/cart.component";
import {PaymentComponent} from "./payment/payment.component";
import {PurchaseHistoryComponent} from "./purchase-history/purchase-history.component";


const routes: Routes = [
  {path:'', component: CartComponent},
  {path: 'payment', component: PaymentComponent},
  {path: 'purchase-history', component: PurchaseHistoryComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartRoutingModule { }
