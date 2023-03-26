import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Category} from "../../model/product/category";
import {Trademark} from "../../model/product/trademark";

@Injectable({
  providedIn: 'root'
})
export class TrademarkService {

  constructor(private httpClient: HttpClient) { }

  showAll(){
    return this.httpClient.get<Trademark[]>("http://localhost:8080/trademark/list/")
  }
}
