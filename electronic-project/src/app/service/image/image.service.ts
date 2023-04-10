import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../../model/product/product";
import {Image} from "../../model/product/image";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private httpClient: HttpClient) { }

  findImageList(id: number){
    return this.httpClient.get<Image[]>("http://localhost:8080/image/list?id="+ id);
  }

  addImage(url: string, productId: number){
    return this.httpClient.post("http://localhost:8080/image/add", {url: url, productId: productId });
  }
}
