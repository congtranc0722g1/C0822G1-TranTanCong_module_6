import { Component, OnInit } from '@angular/core';
import {Category} from "../../model/product/category";
import {CategoryService} from "../../service/product/category.service";

@Component({
  selector: 'app-menu-left',
  templateUrl: './menu-left.component.html',
  styleUrls: ['./menu-left.component.css']
})
export class MenuLeftComponent implements OnInit {
  categoryList: Category[] = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.showAll().subscribe(next =>{
      this.categoryList = next;
    })
  }

}
