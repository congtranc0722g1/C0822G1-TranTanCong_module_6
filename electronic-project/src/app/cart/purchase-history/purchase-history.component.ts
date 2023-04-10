import { Component, OnInit } from '@angular/core';
import {PurchaseService} from "../../service/purchase/purchase.service";
import {Purchase} from "../../model/purchase/purchase";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";
import {PurchaseDetail} from "../../model/purchase/purchase-detail";
import {Title} from "@angular/platform-browser";

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
  id: number;
  purchasePage: any;

  purchaseList: Purchase[] = [];
  purchaseDetailList: PurchaseDetail[] = [];
  size: number = 0;

  constructor(private purchaseService: PurchaseService,
              private token: TokenService,
              private share: ShareService,
              private titleService: Title) {
    this.share.getClickEvent().subscribe(next =>{
      this.getAll(+this.token.getId(), this.page)
    })

    this.titleService.setTitle("Lịch sử mua hàng");
  }

  ngOnInit(): void {
    window.scroll(0,0)
    this.getAll(+this.token.getId(), this.page)
  }

  getAll(id: number, page: number){
    this.id = id;
    this.purchaseService.showAllPurchase(id, page).subscribe(next => {
      this.page = page
      this.purchaseList = next['content'];
      this.purchasePage =  next;
      this.size = next['size'];
    })
  }


  viewDetail(customerName: string, customerPhone: string, customerAddress: string, id: number) {
    this.customerName = customerName;
    this.customerPhone = customerPhone;
    this.customerAddress = customerAddress;
    this.purchaseService.showDetailPurchase(id).subscribe(next=>{
      this.purchaseDetailList = next;
    })
  }
}
