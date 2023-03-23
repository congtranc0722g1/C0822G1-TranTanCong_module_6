import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {CategoryService} from "../../login/service/product/category.service";
import {TrademarkService} from "../../login/service/product/trademark.service";
import {Category} from "../../model/product/category";
import {Trademark} from "../../model/product/trademark";
import {ProductService} from "../../login/service/product/product.service";

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  form: FormGroup = new FormGroup({
    code: new FormControl(),
    name: new FormControl(),
    description: new FormControl(),
    entryPrice: new FormControl(),
    quantity: new FormControl(),
    category: new FormControl(),
    trademark: new FormControl()
  });

  categoryList: Category[] = [];
  trademarkList: Trademark[] = [];

  constructor(private productService: ProductService, private categoryService: CategoryService, private trademarkService: TrademarkService) { }

  ngOnInit(): void {
    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
      console.log(next)
    });

    this.trademarkService.showAll().subscribe(next => {
      this.trademarkList = next;
    });
  }

  createProduct() {
    if (this.form.valid){
      this.productService.addProduct(this.form.value).subscribe(next => {
        alert("Thêm mới thành công")
      })
    }
  }

  reset() {

  }
}
