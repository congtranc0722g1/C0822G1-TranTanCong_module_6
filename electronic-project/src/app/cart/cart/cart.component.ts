import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../service/login/login.service";
import {User} from "../../model/user/user";
import {TokenService} from "../../service/login/token.service";
import {CartService} from "../../service/cart/cart.service";
import {Cart} from "../../model/cart/cart";
import {ShareService} from "../../service/login/share.service";
import {Router} from "@angular/router";
import {SearchProductService} from "../../service/product/search-product.service";
import { render } from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  user: User = {};
  name: '';
  isLogged = false;
  quantity: number;
  cartList: Cart[] = [];
  totalPayment: number;
  usdPayment= 0;


  constructor(private loginService: LoginService,
              private token:TokenService,
              private cartService: CartService,
              private share: ShareService,
              private router: Router) {
    this.share.getClickEvent().subscribe(next => {
      this.getCart(+this.token.getId());
      this.getTotalPayment(+this.token.getId());
    })

    render({
      id: "#buttonPayment",
      currency: "USD",
      value: (this.usdPayment).toFixed(2),
      onApprove: (details) => {
        alert("Ok")
      }
    })
  }

  ngOnInit(): void {
    this.getCart(+this.token.getId());
    this.getTotalPayment(+this.token.getId());
    console.log(this.getTotalPayment(+this.token.getId()))
  }

  increaseQuantity(productId: number, quantity: number) {
    this.cartService.addCart(+this.token.getId(), productId, quantity).subscribe(next => {
      this.share.sendClickEvent();
    })
  }

  // decreaseQuantity() {
  //   if (this.quantityAcv > 0) {
  //     this.quantityAcv--;
  //   }
  // }

  loader() {
    if (this.isLogged) {
      this.loginService.profile(this.token.getId()).subscribe(
        next => {
          this.user = next;
          console.log(next)
        }
      )
    }
  }

  getCart(id: number){
    this.cartService.getCard(id).subscribe(next => {
      this.cartList = next;
      console.log(next)
    })
  }

  getTotalPayment(id: number){
    this.cartService.getTotalPayment(id).subscribe(next => {
      this.totalPayment = next;
      this.usdPayment = next/24000
      console.log(this.usdPayment)
    })
  }

  deletePurchaseDetail(productId: number){
    this.cartService.deletePurchaseDetail(+this.token.getId(), productId).subscribe(next =>{
      alert("Xóa thành công")
      this.share.sendClickEvent();
    })
  }

  updateQuantity(productId: number, quantity: number) {
    this.cartService.updateCart(+this.token.getId(), productId, quantity).subscribe(next => {
      this.share.sendClickEvent();
    })
  }
}
