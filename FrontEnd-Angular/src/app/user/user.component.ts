import { Component, OnInit, Injectable } from '@angular/core';
import { User } from '../classes/user';
import { Subject } from 'rxjs';
import { Http } from '@angular/http';
import { UserService } from './user.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Role } from '../classes/role';
import { HttpClient } from '@angular/common/http';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { RoleService } from '../role/role.service';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
@Injectable()
export class UserComponent implements OnInit {
  user: User = new User();
  users: User[] = [];
  roles: Role[] = [];
  isCreate = true;
  deleteMessage = false;
  isupdated = false;
  dropdownSettings: IDropdownSettings = {};
  functions: Function[] = [];
  constructor(private http: Http, private roleService: RoleService, private router: Router, private userService: UserService, private toastr: ToastrService) { }

  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      this.users = data;
    })

    this.roleService.getAll().subscribe(data => {
      this.roles = data;
    })
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'roleID',
      textField: 'roleName',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 5,
      allowSearchFilter: true
    };

  } 

  create(){
    this.user = new User();
    this.isCreate = true;
  }
  update(user) {
    this.user = user;
    this.isCreate= false;
  };
  delete(user) {
    user.authorities = null;
    this.user = user;
    this.userService.delete(user).subscribe(data => {
      console.log(data);
      if (data == true) {
        this.getData();
        this.toastr.success('Success!', 'Xoa thanh cong', {
          timeOut: 3000
        });
      } else {
        this.toastr.error('Error!', 'Cập nhật không thành công', {
          timeOut: 3000
        });
      }

    })
  };

  getData() {
    this.userService.getAll().subscribe(data => {
      this.users = data;
    })
  }

  userupdateform = new FormGroup({
    userID: new FormControl(),
    userName: new FormControl(),
    status: new FormControl(),
    fullName: new FormControl(),
    gender: new FormControl(),
    role: new FormControl(),
    password : new FormControl()
  });

  updateUser(user) {
    console.log(user);
    user.authorities=null;
    this.userService.save(user).subscribe(data => {
      console.log(data);
      if (data == true) {
        this.getData();
        this.toastr.success('Success!', 'Cập nhật thành công', {
          timeOut: 3000
        });
      } else {
        this.toastr.error('Error!', 'Cập nhật không thành công', {
          timeOut: 3000
        });
      }

    })

  }


  ngOnDestroy(): void {
    // Do not forget to unsubscribe the event
  
  }
  changeisUpdate() {
    this.isupdated = false;
  }

}
