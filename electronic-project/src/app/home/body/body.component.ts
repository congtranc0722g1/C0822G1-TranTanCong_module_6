import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {Category} from "../../model/product/category";
import {CategoryService} from "../../service/product/category.service";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  latestProductList: Product[] = [];

  categoryList: Category[] =[];

  constructor(private productService: ProductService, private categoryService: CategoryService) {
    this.productService.showLatestProductList().subscribe(next => {
      console.log(next)
      this.latestProductList = next;
    });

    this.categoryService.showAll().subscribe(next =>{
      this.categoryList = next;
    })
  }

  ngOnInit(): void {
    window.scroll(0,0)
  }
}
