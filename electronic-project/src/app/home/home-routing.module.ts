import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HeaderComponent} from "./header/header.component";
import {BodyComponent} from "./body/body.component";
import {ErrorComponent} from "./error/error/error.component";


const routes: Routes = [
  {path:'', component: BodyComponent},
  {path:'error', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
