import {Component, OnInit, Input, OnChanges, OnDestroy} from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {FormGroup} from "@angular/forms";
import {Category} from "../../model/product/category";
import {CategoryService} from "../../service/product/category.service";
import {Trademark} from "../../model/product/trademark";
import {TrademarkService} from "../../service/product/trademark.service";
import {SearchProductService} from "../../service/product/search-product.service";
import {HeaderComponent} from "../../home/header/header.component";
import {CartService} from "../../service/cart/cart.service";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";
import {ToastrService} from "ngx-toastr";
import {User} from "../../model/user/user";
import {LoginService} from "../../service/login/login.service";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit, OnDestroy {
  user: User;
  isLogged = false;
  page: number = 0;
  totalPage: number = 0;
  size: number = 0;
  trademark1 = -1;
  category1 = -1;
  saleProductList: Product[] = [];
  nameSearch = '';
  categoryList: Category[] = [];
  trademarkList: Trademark[] = [];
  countProduct: number;
  role = 'none';
  productPage: any;

  constructor(private productService: ProductService,
              private categoryService: CategoryService,
              private  trademarkService: TrademarkService,
              private searchProductService: SearchProductService,
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

    this.searchProductService.currentMessage.subscribe(next => {
        this.category1 = next;
    })
    this.searchProductService.searchCategory.subscribe(next => {
      this.nameSearch = next;
    })
    this.checkHidden(false);
    this.getListCategory();
    this.getListTrademark();
    this.searchSaleProductList(this.category1, this.trademark1, this.nameSearch, this.page);
    this.productService.countProduct().subscribe(next => {
      this.countProduct = next;
    })

    this.titleService.setTitle("Sản phẩm");
  }

  ngOnInit(): void {

    this.role = this.token.getRole();
    window.scroll(0,0);
  }

  getListCategory(){
    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
    });
  }

  getListTrademark(){
    this.trademarkService.showAll().subscribe(next => {
      this.trademarkList = next;
    });
  }

  searchSaleProductList(category: number, trademark: number, name: string, page: number) {
    this.productService.showSaleProductList(category, trademark, name, page).subscribe(next =>{
      this.category1 = category;
      this.trademark1 = trademark;
      this.nameSearch = name;
      this.saleProductList = next['content'];
      this.productPage = next;
      // this.totalPage = next['totalPages'];
      // this.page = next.pageable['pageNumber'];
      // this.size = next['size'];
      // this.searchProductService.searchNameCategory('');
      console.log(next)
    })
  }

  checkHidden(check: boolean){
    this.searchProductService.setHidden(check)
  }

  ngOnDestroy(): void {
    this.checkHidden(true);
  }

  addCart(productId: number){
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
