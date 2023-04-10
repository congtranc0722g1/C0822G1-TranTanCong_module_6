import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProductListComponent} from "./product-list/product-list.component";
import {ProductDetailComponent} from "./product-detail/product-detail.component";
import {ProductCreateComponent} from "./product-create/product-create.component";
import {ProductEditComponent} from "./product-edit/product-edit.component";
import {ProductListManagerComponent} from "./product-list-manager/product-list-manager.component";
import {AdminGuard} from "../login/security/admin.guard";


const routes: Routes = [
  {path:'', component: ProductListComponent},
  {path:'detail/:id', component: ProductDetailComponent},
  {path:'create', canActivate: [AdminGuard], component: ProductCreateComponent},
  {path:'edit/:id', canActivate: [AdminGuard], component: ProductEditComponent},
  {path:'list', canActivate: [AdminGuard], component: ProductListManagerComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
