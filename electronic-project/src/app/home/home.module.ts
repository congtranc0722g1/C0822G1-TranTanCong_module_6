import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HeaderComponent } from './header/header.component';
import { BodyComponent } from './body/body.component';
import { FooterComponent } from './footer/footer.component';
import { MenuLeftComponent } from './menu-left/menu-left.component';
import {ProductModule} from "../product/product.module";
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [HeaderComponent, BodyComponent, FooterComponent, MenuLeftComponent],
  exports: [
    HeaderComponent,
    FooterComponent
  ],
    imports: [
        CommonModule,
        HomeRoutingModule,
        ProductModule,
        FormsModule
    ]
})
export class HomeModule { }
