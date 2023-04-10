import { Component, OnInit } from '@angular/core';
import {Product} from "../../model/product/product";
import {ProductService} from "../../service/product/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {Category} from "../../model/product/category";
import {Trademark} from "../../model/product/trademark";
import {CategoryService} from "../../service/product/category.service";
import {TrademarkService} from "../../service/product/trademark.service";
import {ToastrService} from "ngx-toastr";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit {
  form: FormGroup = new FormGroup({
    id: new FormControl(),
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

  public compareWith(object1: Category, object2: Category){
    return object1 && object2 ? object1.id === object2.id : object1 === object2;
  }

  public compareWith1(object1: Trademark, object2: Trademark){
    return object1 && object2 ? object1.id === object2.id : object1 === object2;
  }

  product: Product;

  constructor(private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private categoryService: CategoryService,
              private trademarkService: TrademarkService,
              private toastrService: ToastrService,
              private router: Router,
              private titleService: Title) {

    this.activatedRoute.paramMap.subscribe(next => {
      const id = +next.get('id');
      this.findProduct(id);
    })

    this.titleService.setTitle("Chỉnh sửa sản phẩm");
  }

  ngOnInit(): void {
    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
    });

    this.trademarkService.showAll().subscribe(next => {
      this.trademarkList = next;
    });
  }

  findProduct(id: number){
    this.productService.findProduct(id).subscribe(next => {
      this.form.patchValue(next);
    })
  }

  updateProduct() {
    if (this.form.valid){
      this.productService.updateProduct(this.form.value).subscribe(next =>{
        this.router.navigateByUrl("/product/list")
        this.toastrService.success("Chỉnh sửa thành công", "Thông báo")
      })
    }
  }

  reset() {

  }
}
