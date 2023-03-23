import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../login/service/product/product.service";
import {Product} from "../../model/product/product";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {

  latestProductList: Product[] = [];

  constructor(private productService: ProductService) {
    this.productService.showLatestProductList().subscribe(next => {
      console.log(next)
      this.latestProductList = next;
    })
  }

  ngOnInit(): void {
    window.scroll(0,0)
  }
}
