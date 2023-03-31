import { Component, OnInit} from '@angular/core';
import {LoginService} from "../../service/login/login.service";
import {User} from "../../model/user/user";
import {TokenService} from "../../service/login/token.service";
import {CartService} from "../../service/cart/cart.service";
import {Cart} from "../../model/cart/cart";
import {ShareService} from "../../service/login/share.service";
import {Router} from "@angular/router";
import {SearchProductService} from "../../service/product/search-product.service";
import {ToastrService} from "ngx-toastr";

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
  totalPayment: number = 0;
  usdPayment= 0;


  constructor(private loginService: LoginService,
              private token:TokenService,
              private cartService: CartService,
              private share: ShareService,
              private router: Router,
              private toastrService: ToastrService) {
    this.share.getClickEvent().subscribe(next => {
      this.getCart(+this.token.getId());
      this.getTotalPayment(+this.token.getId());
    })
  }

  ngOnInit(): void {
    window.scroll(0,50)
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
      this.toastrService.success("Sản phẩm đã bị xóa khỏi giỏ hàng", "Thông báo")
      this.share.sendClickEvent();
    })
  }

  updateQuantity(productId: number, quantity: number) {
    this.cartService.updateCart(+this.token.getId(), productId, quantity).subscribe(next => {
      this.share.sendClickEvent();
    })
  }
}
