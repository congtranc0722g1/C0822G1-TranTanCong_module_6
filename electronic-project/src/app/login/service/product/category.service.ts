import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Category} from "../../../model/product/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) { }

  showAll(){
    return this.httpClient.get<Category[]>("http://localhost:8080/category/list/")
  }
}
