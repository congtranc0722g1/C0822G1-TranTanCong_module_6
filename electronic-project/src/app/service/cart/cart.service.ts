import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Cart} from "../../model/cart/cart";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httpClient: HttpClient) { }

  getCard(id: number){
   return this.httpClient.get<Cart[]>("http://localhost:8080/cart/list?id=" + id)
  }
}
