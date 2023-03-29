import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../service/login/login.service";
import {User} from "../../model/user/user";
import {TokenService} from "../../service/login/token.service";
import {CartService} from "../../service/cart/cart.service";
import {Cart} from "../../model/cart/cart";
import {ShareService} from "../../service/login/share.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  user: User = {};
  name: '';
  isLogged = false;
  quantityAcv: number = 0;
  cartList: Cart[] = [];
  totalPayment: number;


  constructor(private loginService: LoginService, private token:TokenService, private cartService: CartService,
              private share: ShareService) {
    // if (this.token.getId() != null){
    //
    // }
  }

  ngOnInit(): void {
    this.getCart(+this.token.getId());
    this.getTotalPayment(+this.token.getId());
  }

  increaseQuantity() {
    this.quantityAcv++;
  }

  decreaseQuantity() {
    if (this.quantityAcv > 0) {
      this.quantityAcv--;
    }
  }

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
    })
  }

}
