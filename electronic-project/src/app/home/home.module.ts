import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HeaderComponent } from './header/header.component';
import { BodyComponent } from './body/body.component';
import { FooterComponent } from './footer/footer.component';


@NgModule({
  declarations: [HeaderComponent, BodyComponent, FooterComponent],
  exports: [
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule
  ]
})
export class HomeModule { }
