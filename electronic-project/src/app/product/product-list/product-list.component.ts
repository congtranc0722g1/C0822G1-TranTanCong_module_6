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


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit, OnDestroy {
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

  constructor(private productService: ProductService,
              private categoryService: CategoryService,
              private  trademarkService: TrademarkService,
              private searchProductService: SearchProductService,
              private cartService: CartService,
              private token: TokenService) {
    this.searchProductService.currentMessage.subscribe(next => {
        this.category1 = next;
    })
    this.searchProductService.searchCategory.subscribe(next => {
      this.nameSearch = next;
    })
    this.checkHidden(false);
  }

  ngOnInit(): void {
    this.searchSaleProductList(this.category1, this.trademark1, this.nameSearch);
    window.scroll(0,0);

    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
    });

    this.trademarkService.showAll().subscribe(next => {
      this.trademarkList = next;
    });

    this.productService.countProduct().subscribe(next => {
      this.countProduct = next;
    })
  }

  searchSaleProductList(category: number, trademark: number, name: string) {
    this.productService.showSaleProductList(category, trademark, name, this.page).subscribe(next =>{
      this.category1 = category;
      this.trademark1 = trademark;
      this.nameSearch = name;
      this.saleProductList = next['content'];
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
    this.cartService.addCart(+this.token.getId(), productId, 1).subscribe(next => {
      alert("Sản phẩm đã được thêm vào giỏ hàng");
    })
  }
}
