import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {Category} from "../../model/product/category";
import {CategoryService} from "../../service/product/category.service";
import {SearchProductService} from "../../service/product/search-product.service";
import {jsGlobalObjectValue} from "@angular/compiler-cli/src/ngtsc/partial_evaluator/src/known_declaration";
import {Router} from "@angular/router";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  latestProductList: Product[] = [];

  categoryList: Category[] =[];

  category: number;
  // idCategory: number;
  // category: number;

  constructor(private productService: ProductService, private categoryService: CategoryService, private searchProductService: SearchProductService, private router: Router) {
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

    this.searchProductService.currentMessage.subscribe(next => {
      this.category = next;
    })

  }
  newSendCategory(category: number){
    this.router.navigateByUrl('/product');
    this.searchProductService.sendCategory(category);
  }
}
