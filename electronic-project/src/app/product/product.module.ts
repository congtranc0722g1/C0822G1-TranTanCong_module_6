import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ProductCreateComponent } from './product-create/product-create.component';
import { ProductEditComponent } from './product-edit/product-edit.component';
import { ProductListManagerComponent } from './product-list-manager/product-list-manager.component';
import { ImageCreateComponent } from './image-create/image-create.component';


@NgModule({
  declarations: [ProductListComponent, ProductDetailComponent, ProductCreateComponent, ProductEditComponent, ProductListManagerComponent, ImageCreateComponent],
  imports: [
    CommonModule,
    ProductRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class ProductModule { }
