import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../../../model/product/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  showLatestProductList(){
    return this.httpClient.get<Product[]>("http://localhost:8080/product/latest-product");
  }

  // showSaleProductList(category: number, trademark: number, name: string, page: number){
  //   return this.httpClient.get<any>("http://localhost:8080/product/sale-product?category=" + category + "&trademark=" + trademark + "&name=" + name + "&page=" + page);
  // }

  showSaleProductList(category: number, trademark: number, name: string,  page: number) {
    return this.httpClient.get<any>("http://localhost:8080/product/list-product?categoryId=" + category + "&trademarkId=" + trademark + "&name=" + name + "&page=" + page);
  }

  findProduct(id: number){
    return this.httpClient.get<Product>("http://localhost:8080/product/" + id)
  }

  addProduct(product: Product){
    return this.httpClient.post("http://localhost:8080/product/add", product)
  }
}
