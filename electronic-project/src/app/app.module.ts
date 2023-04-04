import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeModule} from "./home/home.module";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginModule} from "./login/login.module";
import {ProductModule} from "./product/product.module";
import {CartModule} from "./cart/cart.module";
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {UserModule} from "./user/user.module";
import {AngularFireModule} from "@angular/fire";
import {environment} from "../environments/environment";
import {AngularFireStorageModule} from "@angular/fire/storage";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HomeModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    LoginModule,
    ProductModule,
    CartModule,
    UserModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireStorageModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
