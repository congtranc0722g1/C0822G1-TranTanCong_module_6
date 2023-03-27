import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchProductService {

  private messageSource = new BehaviorSubject<number>(-1)
  currentMessage = this.messageSource.asObservable();

  constructor() {

  }

  sendCategory(category: number){
    this.messageSource.next(category);
  }
}
