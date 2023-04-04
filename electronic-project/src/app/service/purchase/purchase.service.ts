import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private httpClient: HttpClient) { }

  updatePurchase(userId: number, purchaseStatus: number, customerName: string, customerPhone: string, customerAddress: string){
    return this.httpClient.put("http://localhost:8080/purchase/update", {userId : userId, purchaseStatusId: purchaseStatus, customerName: customerName, customerPhone: customerPhone, customerAddress: customerAddress})
  }

  showAllPurchase(id: number, page: number) {
    return this.httpClient.get<any>("http://localhost:8080/purchase/list-purchase?id=" + id+ "&page=" + page);
  }
}
