import { Component, OnInit, Input } from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {FormGroup} from "@angular/forms";
import {Category} from "../../model/product/category";
import {CategoryService} from "../../service/product/category.service";
import {Trademark} from "../../model/product/trademark";
import {TrademarkService} from "../../service/product/trademark.service";
import {SearchProductService} from "../../service/product/search-product.service";


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
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
  @Input() idCategory: number;
  sendCategory: number;

  constructor(private productService: ProductService, private categoryService: CategoryService, private  trademarkService: TrademarkService, private searchProductService: SearchProductService) {
    this.searchProductService.currentMessage.subscribe(next => {
      this.category1 = next;
    })
  }

  ngOnInit(): void {
    this.searchSaleProductList(this.category1, this.trademark1, this.nameSearch);
    window.scroll(0,0);

    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
      console.log("Ok" + next)
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
      console.log(next)
    })
  }
}
