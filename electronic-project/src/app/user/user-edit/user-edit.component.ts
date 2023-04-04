import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {User} from "../../model/user/user";
import {LoginService} from "../../service/login/login.service";
import {TokenService} from "../../service/login/token.service";
import {ShareService} from "../../service/login/share.service";
import {UserService} from "../../service/user/user.service";
import {ToastrService} from "ngx-toastr";
import {formatDate} from "@angular/common";
import {AngularFireStorage} from "@angular/fire/storage";
import {finalize} from "rxjs/operators";
import {Observable} from "rxjs";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {
  user: User;
  isLogged = false;
  selectedImage: any = null;
  form: FormGroup = new FormGroup({
    id: new FormControl(),
    name: new FormControl(),
    gender: new FormControl(),
    email: new FormControl(),
    dateOfBirth: new FormControl(),
    phone: new FormControl(),
    address: new FormControl(),
    avatar: new FormControl()
  });
  downloadURL: Observable<string> | undefined;
  src: string | undefined;

  constructor(private loginService: LoginService,
              private token: TokenService,
              private share: ShareService,
              private userService: UserService,
              private toastrService: ToastrService,
              @Inject(AngularFireStorage) private storage: AngularFireStorage) {
    this.share.getClickEvent().subscribe(next => {
      this.loader();
    })
    this.isLogged = this.token.isLogger()
    this.loader()
  }

  ngOnInit(): void {
    const profilePic = document.querySelector('.profile-pic');
    const changePicBtn = profilePic.querySelector('.change-pic-btn');

    profilePic.addEventListener('mouseover', function () {
      changePicBtn.classList.add('hover');
    });

    profilePic.addEventListener('mouseout', function () {
      changePicBtn.classList.remove('hover');
    });

    console.log("alo" + this.selectedImage.name)

  }

  updateUser() {
    this.userService.updateUser(this.form.value).subscribe(next => {
      this.share.sendClickEvent();
      this.toastrService.success("Cập nhật thành công", "Thông báo")
    })
  }

  loader() {
    if (this.isLogged) {
      this.loginService.profile(this.token.getId()).subscribe(
        next => {
          this.user = next;
          this.form.patchValue(next)
        }
      )
    }
  }

  // showReview(event: any) {
  //   this.selectedImage = event.target.file[0];
  //   console.log(this.selectedImage)
  //   const nameImg = this.getCurrentDateTime() + this.selectedImage.name;
  //   const fileRef = this.storage.ref(nameImg);
  //   this.storage.upload(nameImg, this.selectedImage).snapshotChanges().pipe(
  //     finalize(() => {
  //       fileRef.getDownloadURL().subscribe((url) => {
  //         this.user.avatar = url;
  //         alert(url)
  //       });
  //     })
  //   ).subscribe();
  // }
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

            this.userService.updateAvatarUser(url, this.user.id).subscribe(next =>{
              this.toastrService.success("Cập nhật ảnh đại diện thành công", "Thông báo")
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
    console.log(this.selectedImage.name)
    const nameImg = this.getCurrentDateTime() + this.selectedImage.name;
    const fileRef = this.storage.ref(nameImg);
    this.storage.upload(nameImg, this.selectedImage).snapshotChanges().pipe(
      finalize(() => {
        fileRef.getDownloadURL().subscribe((url) => {
          alert(url)
        });
      })
    ).subscribe();
  }
}
