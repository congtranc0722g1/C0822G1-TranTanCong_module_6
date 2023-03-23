import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../login/service/product/product.service";
import {Product} from "../../model/product/product";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product: Product = {};

  constructor(private activatedRoute: ActivatedRoute, private productService: ProductService) {
    this.activatedRoute.paramMap.subscribe(next => {
      const id = +next.get('id');
      this.findProduct(id);
    })
  }

  ngOnInit(): void {
    window.scroll(0,50)
  }

  findProduct(id: number){
    this.productService.findProduct(id).subscribe(next => {
      this.product = next;
    })
  }

}
