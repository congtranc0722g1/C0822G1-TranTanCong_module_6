import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {ActivatedRoute, Router} from "@angular/router";
import {CartService} from "../../service/cart/cart.service";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";
import {ToastrService} from "ngx-toastr";
import {User} from "../../model/user/user";
import {LoginService} from "../../service/login/login.service";
import Swal from "sweetalert2";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  user: User;
  isLogged = false;
  product: Product = {};
  quantity: number = 1;
  productListByCategory: Product[] = [];
  categoryId: number;
  role = 'none';

  constructor(private activatedRoute: ActivatedRoute,
              private productService: ProductService,
              private cartService: CartService,
              private token: TokenService,
              private share: ShareService,
              private toastrService: ToastrService,
              private loginService: LoginService,
              private router: Router,
              private titleService: Title) {

    this.isLogged = this.token.isLogger()
    this.loader()

    this.share.getClickEvent().subscribe(next =>{
      this.isLogged = this.token.isLogger()
      this.loader();
      this.role = this.token.getRole();
    })

    this.activatedRoute.paramMap.subscribe(next => {
      const id = +next.get('id');
      this.findProduct(id);
    })

    this.titleService.setTitle("Chi tiết sản phẩm");
  }

  ngOnInit(): void {
    this.role = this.token.getRole();
    window.scroll(0,50)
    this.findProductByCategory(this.categoryId);
  }

  findProduct(id: number){
    this.productService.findProduct(id).subscribe(next => {
      this.product = next;
      this.categoryId = next.category.id
      this.findProductByCategory(next.category.id);
    })
  }

  addCart(productId: number){
    if (this.isLogged){
      this.cartService.addCart(+this.token.getId(), productId, this.quantity).subscribe(next => {
        this.share.sendClickEvent();
        this.toastrService.success("Sản phẩm đã được thêm vào giỏ hàng", "Thông báo")
      }, error => {
        if (error.error === 'exceedTheAmount'){
          this.toastrService.warning("Bạn đã nhập quá số lượng hàng trong kho, Hãy kiểm tra giỏ hàng của bạn", "Thông báo")
        }else if(error.error === 'errorInputQuantity'){
          this.toastrService.warning("Số lượng sản phẩm cần mua phải lớn hơn hoặc bằng 1", "Thông báo")
          this.quantity = 1;
        } else {
          this.toastrService.warning("Số lượng phải là một số", "Thông báo")
          this.quantity = 1;
        }

      })
    }else {
      Swal.fire({
        title: "Bạn chưa đăng nhập!",
        text: "Cần đăng nhập mới có thể thêm sản phẩm vào giỏ hàng",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#007bff",
        confirmButtonText: "Đăng nhập!",
        cancelButtonText: "Hủy",
      }).then((result) =>{
        if (result.isConfirmed) {
          this.router.navigate(['/login'])
        }
      })
    }

  }

  increaseQuantity() {
    this.quantity++;
  }

  decreaseQuantity() {
    if (this.quantity >= 1) {
      this.quantity--;
    }
  }

  findProductByCategory(categoryId: number){
    this.productService.findProductByCategory(categoryId).subscribe(next => {
      this.productListByCategory = next;
      console.log(next)
      }
    )
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
}
