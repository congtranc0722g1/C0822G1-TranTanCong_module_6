import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {ActivatedRoute, Router} from "@angular/router";
import {CartService} from "../../service/cart/cart.service";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product: Product = {};
  quantity: number = 1;

  constructor(private activatedRoute: ActivatedRoute,
              private productService: ProductService,
              private cartService: CartService,
              private token: TokenService,
              private share: ShareService,
              private toastrService: ToastrService) {
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

  addCart(productId: number){
    this.cartService.addCart(+this.token.getId(), productId, this.quantity).subscribe(next => {
      this.share.sendClickEvent();
      this.toastrService.success("Sản phẩm đã được thêm vào giỏ hàng", "Thông báo")
    })
  }

  increaseQuantity() {
    this.quantity++;
  }

  decreaseQuantity() {
    if (this.quantity >= 1) {
      this.quantity--;
    }
  }

}
