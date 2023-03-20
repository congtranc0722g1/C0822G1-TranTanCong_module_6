import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../../model/product/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  showLatestProductList(){
    return this.httpClient.get<Product[]>("http://localhost:8080/product/latest-product");
  }
}
