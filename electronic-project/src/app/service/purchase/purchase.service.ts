import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PurchaseDetail} from "../../model/purchase/purchase-detail";

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private httpClient: HttpClient) { }

  updatePurchase(purchase: any){
    return this.httpClient.put("http://localhost:8080/purchase/update", {userId : purchase.userId, purchaseStatusId: purchase.purchaseStatus, customerName: purchase.name, customerPhone: purchase.phone, customerAddress: purchase.address})
  }

  showAllPurchase(id: number, page: number) {
    return this.httpClient.get<any>("http://localhost:8080/purchase/list-purchase?id=" + id+ "&page=" + page);
  }

  showDetailPurchase(id: number) {
    return this.httpClient.get<PurchaseDetail[]>("http://localhost:8080/purchase/list-purchase-detail?id=" + id);
  }
}
