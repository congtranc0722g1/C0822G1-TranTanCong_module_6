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

  addCart(userId: number, productId: number, quantity: number){
    return this.httpClient.post("http://localhost:8080/cart/add", {userId : userId, productId: productId, quantity: quantity})
  }

  getTotalPayment(id: number){
    return this.httpClient.get<number>("http://localhost:8080/cart/total-payment?id=" + id)
  }
}
