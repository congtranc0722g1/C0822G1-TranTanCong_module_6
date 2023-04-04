import { Component, OnInit } from '@angular/core';
import {PurchaseService} from "../../service/purchase/purchase.service";
import {Purchase} from "../../model/purchase/purchase";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {
  page: number = 0;

  customerName: string;
  customerPhone: string;
  customerAddress: string;

  purchaseList: Purchase[] = [];

  constructor(private purchaseService: PurchaseService,
              private token: TokenService,
              private share: ShareService) {
    this.share.getClickEvent().subscribe(next =>{
      this.getAll(+this.token.getId(), this.page)
    })
  }

  ngOnInit(): void {
    this.getAll(+this.token.getId(), this.page)
  }

  getAll(id: number, page: number){
    this.purchaseService.showAllPurchase(id, page).subscribe(next => {
      this.purchaseList = next['content'];
      console.log(next)
    })
  }


  viewDetail(customerName: string, customerPhone: string, customerAddress: string) {
    this.customerName = customerName;
    this.customerPhone = customerPhone;
    this.customerAddress = customerAddress;
  }
}
