import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {CategoryService} from "../../service/product/category.service";
import {TrademarkService} from "../../service/product/trademark.service";
import {Category} from "../../model/product/category";
import {Trademark} from "../../model/product/trademark";
import {ProductService} from "../../service/product/product.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";

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

  constructor(private productService: ProductService,
              private categoryService: CategoryService,
              private trademarkService: TrademarkService,
              private toastrService: ToastrService,
              private router: Router,
              private titleService: Title) {

    this.titleService.setTitle("Thêm mới sản phẩm");
  }

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
        this.toastrService.success("Thêm mới thành công", "Thông báo")
        this.router.navigateByUrl("/product/list")
      })
    }
  }

  reset() {
  }
}
