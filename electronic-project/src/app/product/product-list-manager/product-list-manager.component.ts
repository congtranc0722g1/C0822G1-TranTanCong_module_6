import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {CategoryService} from "../../service/product/category.service";
import {TrademarkService} from "../../service/product/trademark.service";
import {Category} from "../../model/product/category";
import {Trademark} from "../../model/product/trademark";

@Component({
  selector: 'app-product-list-manager',
  templateUrl: './product-list-manager.component.html',
  styleUrls: ['./product-list-manager.component.css']
})
export class ProductListManagerComponent implements OnInit {

  page: number = 0;
  totalPage: number = 0;
  size: number = 0;
  trademark = -1;
  category = -1;
  productListManager: Product[] = [];
  nameSearch = '';
  totalElements = 0;
  product: Product = {};
  categoryList: Category[] = [];
  trademarkList: Trademark[] = [];

  constructor(private  productService: ProductService, private categoryService: CategoryService, private trademarkService: TrademarkService) { }

  ngOnInit(): void {
    this.showListProductManager(this.category, this.trademark, this.nameSearch);

    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
    })

    this.trademarkService.showAll().subscribe(next =>{
      this.trademarkList = next;
    })
  }

  showListProductManager(category: number, trademark: number, name: string){
    this.productService.showSaleProductList(category, trademark, name, this.page).subscribe(next => {
      this.category = category;
      this.trademark = trademark;
      this.nameSearch = name;
      this.productListManager = next['content'];
    })
  }

}
