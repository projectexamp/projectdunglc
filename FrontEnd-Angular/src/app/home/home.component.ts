import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { FormGroup, FormControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { LoginService } from '../services/login.service';
import { User } from '../classes/user';
import { Function } from '../classes/function';
import { UserService } from '../user/user.service';
import { FunctionService } from '../function/function.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private route: ActivatedRoute, private router: Router, private toastr: ToastrService, private loginService: LoginService, private functionService: FunctionService) { }
  currentUser: User = new User();
  pass: any = {};
  user: User = new User();
  functions: Function[] = [];
  ngOnInit() {
    this.getMenufromToken();
  }

  async getMenufromToken() {
    this.functionService.getCurrentFunction(localStorage.getItem('token')).subscribe(result => {
      this.functions = result;
      console.log(this.functions);
    }, error => {
      this.toastr.error('Error!', 'Có lỗi xảy ra', {
        timeOut: 3000
      });
      return false;
    });
  }

  addFunction(func) {
    let check = true;
    this.functions.forEach(function (f) {
      if (func! = null && func.functionID == f.functionID) {
        check = false;
      }
    });
    if(check){
      this.functions.push(func);
    }
  }

  openModal() {
    this.pass = {};
  }

  changePassword() {
    if (this.pass.oldPassword != null && this.pass.newPassword != null && this.pass.rePassword != null) {
      if (this.pass.newPassword != this.pass.rePassword) {
        this.toastr.error('Error!', 'Mật khẩu mới không khớp', {
          timeOut: 3000
        });
      } else {
        this.checkPass();
      }

    } else {
      this.toastr.error('Error!', 'vui lòng nhập đủ thông tin', {
        timeOut: 3000
      });
    }
  }
  checkPass() {

    this.user.userName = localStorage.getItem('userName');
    this.user.password = this.pass.oldPassword;

    this.loginService.checkPass(this.user).subscribe(
      result => {

        if (result == true) {
          this.user.userName = localStorage.getItem('userName');
          this.user.password = this.pass.newPassword;
          this.user.userID = localStorage.getItem('userID');
          this.loginService.changePass(this.user).subscribe(
            data => {
              if (data == true) {
                this.toastr.success('Success!', 'Đổi mật khẩu thàn công', {
                  timeOut: 3000
                });
              }
              else {
                this.toastr.error('Error!', 'Thất bại có lỗi xảy ra', {
                  timeOut: 3000
                });

              }
            },
            error => {
              this.toastr.error('Error!', 'Thất bại có lỗi xảy ra', {
                timeOut: 3000
              });
            }
          );
        }
        else {
          this.toastr.error('Error!', 'Sai mật khẩu', {
            timeOut: 3000
          });
          return false;
        }
      },
      error => {
        this.toastr.error('Error!', 'Sai mật khẩu', {
          timeOut: 3000
        });
        return false;
      }
    );

  }
  functionupdateform = new FormGroup({
    oldPassword: new FormControl(),
    newPassword: new FormControl(),
    rePassword: new FormControl(),

  });
}
