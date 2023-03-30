import { Component } from '@angular/core';
import { render } from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'test-payment';
  constructor() {
    render({
      id: "#a",
      currency: "USD",
      value: "100.00",
      onApprove: (details => {
        alert("Ok")
      })
    })
  }
}
