import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminGuard} from "./login/security/admin.guard";


const routes: Routes = [
  {path: '', loadChildren: () => import("./home/home-routing.module").then(module => module.HomeRoutingModule)},
  {path: 'login', loadChildren: () => import("./login/login-routing.module").then(module => module.LoginRoutingModule)},
  {path: 'cart', loadChildren: () => import("./cart/cart-routing.module").then(module => module.CartRoutingModule)},
  {path: 'product', loadChildren: () => import("./product/product-routing.module").then(module => module.ProductRoutingModule)},
  {path: 'user', loadChildren: () => import("./user/user-routing.module").then(module => module.UserRoutingModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
