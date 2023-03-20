import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  quantityAcv: number = 0;

  constructor() { }

  ngOnInit(): void {
  }

  increaseQuantity() {
    this.quantityAcv++;
  }

  decreaseQuantity() {
    if (this.quantityAcv > 0) {
      this.quantityAcv--;
    }
  }


}
