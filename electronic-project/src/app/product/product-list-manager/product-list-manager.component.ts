import {Component, Inject, OnInit} from '@angular/core';
import {ProductService} from "../../service/product/product.service";
import {Product} from "../../model/product/product";
import {CategoryService} from "../../service/product/category.service";
import {TrademarkService} from "../../service/product/trademark.service";
import {Category} from "../../model/product/category";
import {Trademark} from "../../model/product/trademark";
import {TokenService} from "../../service/login/token.service";
import {Image} from "../../model/product/image";
import {ImageService} from "../../service/image/image.service";
import {finalize} from "rxjs/operators";
import {AngularFireStorage} from "@angular/fire/storage";
import {Observable} from "rxjs";
import {formatDate} from "@angular/common";
import {ToastrService} from "ngx-toastr";
import {ShareService} from "../../service/login/share.service";
import {Router} from "@angular/router";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-product-list-manager',
  templateUrl: './product-list-manager.component.html',
  styleUrls: ['./product-list-manager.component.css']
})
export class ProductListManagerComponent implements OnInit {

  imageList: Image[] = [];
  productPage: any;
  page: number = 0;
  totalPage: number = 0;
  size: number = 0;
  trademark = -1;
  category = -1;
  productListManager: Product[] = [];
  nameSearch = '';
  totalElements = 0;
  product: Product;
  categoryList: Category[] = [];
  trademarkList: Trademark[] = [];
  downloadURL: Observable<string> | undefined;
  src: string | undefined;
  selectedImage: any = null;
  private productId: number;

  constructor(private  productService: ProductService,
              private categoryService: CategoryService,
              private trademarkService: TrademarkService,
              private imageService: ImageService,
              @Inject(AngularFireStorage) private storage: AngularFireStorage,
              private toastrService: ToastrService,
              private share: ShareService,
              private router: Router,
              private titleService: Title) {
    this.share.getClickEvent().subscribe(next=>{
      this.getListImage(this.productId);
      this.showListProductManager(this.category, this.trademark, this.nameSearch, this.page);
    })

    this.titleService.setTitle("Quản lý sản phẩm");
  }

  ngOnInit(): void {
    this.showListProductManager(this.category, this.trademark, this.nameSearch, this.page);

    this.categoryService.showAll().subscribe(next => {
      this.categoryList = next;
    })

    this.trademarkService.showAll().subscribe(next =>{
      this.trademarkList = next;
    })
  }

  showListProductManager(category: number, trademark: number, name: string, page: number){
    this.productService.showManagerProductList(category, trademark, name, page).subscribe(next => {
      this.category = category;
      this.trademark = trademark;
      this.nameSearch = name;
      this.page = page;
      this.productListManager = next['content'];
      this.productPage = next;
      this.size = next['size'];
    })
  }

  getListImage(id: number){
    this.imageService.findImageList(id).subscribe(next=>{
      this.imageList = next;
      console.log(next)
      this.productId = id;
    })
  }

  showPreview(event: any) {
    this.selectedImage = event.target.files[0];
    console.log(this.selectedImage)
    const filePath = this.getCurrentDateTime()+ this.selectedImage.name;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(filePath, this.selectedImage);
    task
      .snapshotChanges()
      .pipe(
        finalize(() => {
          fileRef.getDownloadURL().subscribe(url => {

            // lấy lại url
            console.log(url)
            this.imageService.addImage(url, this.productId).subscribe(next=>{
              this.toastrService.success("Thêm ảnh thành công", "Thông báo")
              this.share.sendClickEvent();
            })
          });
        })
      )
      .subscribe();
  }

  getCurrentDateTime(): string {
    return formatDate(new Date(), 'dd-MM-yyyyhhmmssa', 'en-US');
  }

  update() {
    this.router.navigateByUrl("/product/edit/" + this.product.id)
  }

  delete() {
    this.productService.deleteProduct(this.product).subscribe(next => {
      this.toastrService.success("Xóa thành công", "Thông báo")
      this.share.sendClickEvent()
    })
  }
}
