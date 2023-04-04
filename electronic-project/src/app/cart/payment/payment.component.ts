import {Component, OnInit, OnDestroy} from '@angular/core';
import {LoginService} from "../../service/login/login.service";
import {TokenService} from "../../service/login/token.service";
import {CartService} from "../../service/cart/cart.service";
import {ShareService} from "../../service/login/share.service";
import {Router} from "@angular/router";
import {Cart} from "../../model/cart/cart";
import {User} from "../../model/user/user";
import {render} from "creditcardpayments/creditCardPayments";
import {PurchaseService} from "../../service/purchase/purchase.service";
import {ProductService} from "../../service/product/product.service";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  user: User;
  isLogged = false;
  cartList: Cart[] = [];
  totalPayment: number = 0;
  usdPayment: number;
  temp: number = 1;
  customerName: string;
  customerPhone: string;
  customerAddress: string;

  constructor(private loginService: LoginService,
              private token: TokenService,
              private cartService: CartService,
              private share: ShareService,
              private router: Router,
              private purchaseService: PurchaseService,
              private productService: ProductService) {
    this.isLogged = this.token.isLogger()
    this.loader()
    this.share.getClickEvent().subscribe(next => {
      this.isLogged = this.token.isLogger()
      this.loader();
      this.getTotalPayment(+this.token.getId())
    })
    this.getCart(+this.token.getId())

    this.getTotalPayment(+this.token.getId())
  }

  ngOnInit(): void {
  }

  getTotalPayment(id: number) {
    this.cartService.getTotalPayment(id).subscribe(next => {
      this.totalPayment = next;
      this.usdPayment = next / 24000;
      render({
        id: "#buttonPayment",
        currency: "USD",
        value: (this.usdPayment).toFixed(2),
        onApprove: (details) => {
          console.log(this.customerName)
          this.updatePurchase();
          this.router.navigateByUrl("/cart/purchase-history")
          this.productService.updateQuantityProduct(this.cartList).subscribe(next => {
            this.share.sendClickEvent();
          })
        }
      })
    })
  }

  updatePurchase(){
    this.purchaseService.updatePurchase(+this.token.getId(), 2, this.user.name, this.user.phone, this.user.address).subscribe(next =>{
    });
  }

  getCart(id: number) {
    this.cartService.getCard(id).subscribe(next => {
      this.cartList = next;
      console.log(next)
    })
  }

  loader() {
    if (this.isLogged) {
      this.loginService.profile(this.token.getId()).subscribe(
        next => {
          this.user = next;
        }
      )
    }
  }

  getTemp(x: number) {
    this.temp = x;
    this.share.sendClickEvent();
  }

  test(){
    this.temp = this.temp
  }

  ngOnDestroy(): void {
    this.share.sendClickEvent();
  }

  paymentInfo(name: string, phone: string, address: string) {
    console.log(name)
    this.customerName = name;
    this.customerPhone = phone;
    this.customerAddress = address;
  }
}
