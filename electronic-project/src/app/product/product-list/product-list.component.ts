import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../login/service/product/product.service";
import {Product} from "../../model/product/product";
import {FormGroup} from "@angular/forms";
import {Category} from "../../model/product/category";
import {CategoryService} from "../../login/service/product/category.service";


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  page: number = 0;
  totalPage: number = 0;
  size: number = 0;
  trademark = -1;
  category = -1;
  saleProductList: Product[] = [];
  nameSearch = '';
  totalElements = 0;
  categoryList: Category[] = [];

  constructor(private productService: ProductService, private categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this.searchSaleProductList(this.category, this.trademark, this.nameSearch);
    window.scroll(0,0);

    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
    })
  }

  searchSaleProductList(category: number, trademark: number, name: string) {
    this.productService.showSaleProductList(category, trademark, name, this.page).subscribe(next =>{
      this.category = category;
      this.trademark = trademark;
      this.nameSearch = name;
      this.saleProductList = next['content'];
      this.totalElements = next['totalElements']
      console.log(next)
    })
  }
}
