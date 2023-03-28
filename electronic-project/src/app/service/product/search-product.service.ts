import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Product} from "../../model/product/product";

@Injectable({
  providedIn: 'root'
})
export class SearchProductService {

  private messageSource = new BehaviorSubject<number>(-1)
  currentMessage = this.messageSource.asObservable();

  private nameCategory = new BehaviorSubject<string>("")
  searchCategory = this.nameCategory.asObservable();

  private isHidden = new BehaviorSubject<boolean>(true)
  checkHidden = this.isHidden.asObservable();

  constructor() {

  }

  sendCategory(category: number){
    this.messageSource.next(category);
  }

  searchNameCategory(name: string){
    this.nameCategory.next(name);
  }

  setHidden(check: boolean){
    this.isHidden.next(check);
  }
}
