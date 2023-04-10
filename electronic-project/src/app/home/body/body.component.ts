import {Component, OnInit} from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {Category} from "../../model/product/category";
import {CategoryService} from "../../service/product/category.service";
import {SearchProductService} from "../../service/product/search-product.service";
import {jsGlobalObjectValue} from "@angular/compiler-cli/src/ngtsc/partial_evaluator/src/known_declaration";
import {Router} from "@angular/router";
import {TokenService} from "../../service/login/token.service";
import {CartService} from "../../service/cart/cart.service";
import {ShareService} from "../../service/login/share.service";
import {ToastrService} from "ngx-toastr";
import {RevenueProduct} from "../../model/product/revenue-product";
import {User} from "../../model/user/user";
import {LoginService} from "../../service/login/login.service";
import Swal from "sweetalert2";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  latestProductList: Product[] = [];
  categoryList: Category[] = [];
  user: User;
  isLogged = false;
  revenueProductList: RevenueProduct[] = [];

  category: number;
  role = 'none';
  // idCategory: number;
  // category: number;

  constructor(private productService: ProductService,
              private categoryService: CategoryService,
              private searchProductService: SearchProductService,
              private router: Router,
              private share: ShareService,
              private token: TokenService,
              private cartService: CartService,
              private toastrService: ToastrService,
              private loginService: LoginService,
              private titleService: Title) {

    this.isLogged = this.token.isLogger()
    this.loader()

    this.share.getClickEvent().subscribe(next =>{
      this.isLogged = this.token.isLogger()
      this.loader();
      this.role = this.token.getRole();
    })

    this.productService.showLatestProductList().subscribe(next => {
      console.log(next)
      this.latestProductList = next;
    });

    this.productService.showRevenueProduct().subscribe(next => {
      this.revenueProductList = next;
    });

    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
    })

    this.titleService.setTitle("Trang chủ");
  }

  ngOnInit(): void {
    this.role = this.token.getRole();

    window.scroll(0, 0)

    this.searchProductService.currentMessage.subscribe(next => {
      this.category = next;
    })

    this.productService.showRevenueProduct().subscribe(next => {
      this.revenueProductList = next;
    })
  }

  newSendCategory(category: number) {
    this.router.navigateByUrl('/product');
    this.searchProductService.sendCategory(category);
  }

  addCart(productId: number) {
    if (this.isLogged){
      this.cartService.addCart(+this.token.getId(), productId, 1).subscribe(next => {
        this.share.sendClickEvent();
        this.toastrService.success("Sản phẩm đã được thêm vào giỏ hàng", "Thông báo")
      }, error => {
        if (error.error === 'exceedTheAmount'){
          this.toastrService.warning("Số lượng hàng trong kho không đủ", "Thông báo")
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
