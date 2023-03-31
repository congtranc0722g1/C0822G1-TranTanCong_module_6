import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private httpClient: HttpClient) { }

  updatePurchase(userId: number, purchaseStatus: number){
    return this.httpClient.put("http://localhost:8080/purchase/update", {userId : userId, purchaseStatusId: purchaseStatus})
  }
}
