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
import Swal from "sweetalert2";
import {Title} from "@angular/platform-browser";

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
              private toastrService: ToastrService,
              private titleService: Title) {

    this.isLogged = this.token.isLogger()
    this.loader()
    this.share.getClickEvent().subscribe(next => {
      this.isLogged = this.token.isLogger()
      this.loader();
    })
    this.share.getClickEvent().subscribe(next => {
      this.getCart(+this.token.getId());
      this.getTotalPayment(+this.token.getId());
    })

    this.titleService.setTitle("Giỏ hàng");
  }

  ngOnInit(): void {
    window.scroll(0,50)
    this.getCart(+this.token.getId());
    this.getTotalPayment(+this.token.getId());
    console.log(this.getTotalPayment(+this.token.getId()))
  }

  increaseQuantity(productId: number, quantity: number, name: string) {
    this.cartService.addCart(+this.token.getId(), productId, quantity).subscribe(next => {
      this.share.sendClickEvent();
    }, error => {
      if (error.error === 'errorQuantity'){
        Swal.fire({
          title: "Số lượng sản phẩm cần mua phải lớn hơn hoặc bằng 1",
          text: "Bạn có muốn xóa sản phẩm: " +name+ " khỏi giỏ hàng không?",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#007bff",
          confirmButtonText: "Xóa",
          cancelButtonText: "Hủy",
        }).then((result) =>{
          if (result.isConfirmed) {
            this.cartService.deletePurchaseDetail(+this.token.getId(), productId).subscribe(next =>{
              this.toastrService.success("Sản phẩm đã bị xóa khỏi giỏ hàng", "Thông báo")
              this.share.sendClickEvent();
            })
          }
        })
      }
      if (error.error ==='exceedTheAmount'){
        this.toastrService.warning("Bạn đã nhập quá số lượng trong kho", "Thông báo")
      }
    })
  }

  // decreaseQuantity() {
  //   if (this.quantityAcv > 0) {
  //     this.quantityAcv--;
  //   }
  // }
  item: number;

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

  deletePurchaseDetail(productId: number, name: string){
    Swal.fire({
      title: "Bạn có muốn xóa sản phẩm: "+name+ " ra khỏi giỏ hàng không?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#007bff",
      confirmButtonText: "Xóa",
      cancelButtonText: "Hủy",
    }).then((result) =>{
      if (result.isConfirmed) {
        this.cartService.deletePurchaseDetail(+this.token.getId(), productId).subscribe(next =>{
          this.toastrService.success("Sản phẩm đã bị xóa khỏi giỏ hàng", "Thông báo")
          this.share.sendClickEvent();
        })
      }
    })
  }

  updateQuantity(productId: number, quantity: number, name: string) {
    this.cartService.updateCart(+this.token.getId(), productId, quantity).subscribe(next => {
      this.share.sendClickEvent();
    }, error => {
      if (error.error === 'errorQuantity') {
        Swal.fire({
          title: "Số lượng sản phẩm cần mua phải lớn hơn hoặc bằng 1",
          text: "Bạn có muốn xóa sản phẩm: " +name+ " khỏi giỏ hàng không?",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#007bff",
          confirmButtonText: "Xóa",
          cancelButtonText: "Hủy",
        }).then((result) =>{
          if (result.isConfirmed) {
            this.cartService.deletePurchaseDetail(+this.token.getId(), productId).subscribe(next =>{
              this.toastrService.success("Sản phẩm đã bị xóa khỏi giỏ hàng", "Thông báo")
            })
          }
        })
      }

      if (error.error === 'errorQuantityFormat') {
        this.toastrService.warning("số lượng cần mua phải là một số", "Thông báo")
      }

      if (error.error === 'exceedTheAmount'){
        this.toastrService.warning("Bạn đã nhập quá số lượng trong kho", "Thông báo")
      }
      this.share.sendClickEvent()
    })
  }

  payment(){
    if (this.user.name === null || this.user.phone === null || this.user.address === null){
      Swal.fire({
        title: "Không thể thanh toán!",
        text: "Cần cập nhật đầy đủ tên, số điện thoại và địa chỉ trước khi thanh toán",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#007bff",
        confirmButtonText: "Cập nhật!",
        cancelButtonText: "Hủy",
      }).then((result) =>{
        if (result.isConfirmed) {
          this.router.navigate(['/user/profile'])
        }
      })
    }else {
      this.router.navigateByUrl("/cart/payment")
    }

  }
}
