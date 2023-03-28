import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../service/login/token.service";
import {User} from "../../model/user/user";
import {LoginService} from "../../service/login/login.service";
import {ShareService} from "../../service/login/share.service";
import {Router} from "@angular/router";
import {SearchProductService} from "../../service/product/search-product.service";
import {Product} from "../../model/product/product";


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user:User
  isLogged = false;
  close = true;
  name: '';
  temp: string = '';
  productList: Product[] = [];
  isHidden: boolean = true;
  constructor(private router:Router,
              private token:TokenService,
              private loginService:LoginService,
              private share:ShareService,
              private searchProductService: SearchProductService) {

    this.token.getUserNameLoggedIn().subscribe(next => {this.name = next
      this.isLogged = this.token.isLogger();
      this.loader();
      this.share.getClickEvent().subscribe(next => {
        this.loader()
      })
    });

    this.searchProductService.checkHidden.subscribe(next => {
      this.isHidden = next;
    })
  }

  ngOnInit(): void {

    this.isLogged = this.token.isLogger();

    this.share.getClickEvent().subscribe(
      next => {
        this.loader()
      }
    )
      this.loader();
      this.share.getClickEvent().subscribe(
        next => {
          this.loader()
        }
      )
  }
  logout() {
    this.close = false;
    this.isLogged = false;
    this.token.logout()
    this.share.sendClickEvent()

  }
  loader() {
    if (this.isLogged) {
      this.loginService.profile(this.token.getId()).subscribe(
        next => {
          this.user = next;
          console.log(next)
        }
      )
    }
  }
  searchName(name: string){
    this.router.navigateByUrl('/product');
    this.searchProductService.searchNameCategory(name);
  }
}
