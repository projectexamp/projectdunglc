import { Component, OnInit, OnDestroy } from '@angular/core';
import { Role } from '../classes/role';
import { Subject } from 'rxjs';
import { Http } from '@angular/http';
import { RoleService } from './role.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { FunctionService } from '../function/function.service';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnDestroy, OnInit {
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  roles: Role[] = [];
  deleteMessage = false;
  isupdated = false;
  role: Role = new Role();
  dropdownSettings: IDropdownSettings = {};
  functions: Function[] = [];
  constructor(private http: Http, private roleservice: RoleService,
    private router: Router, private functionService: FunctionService, private toastr: ToastrService) { }

  ngOnInit(): void {

    this.getData();

    this.functionService.getAll().subscribe(data => {
      this.functions = data;
    })
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 2
    };

    this.dropdownSettings = {
      singleSelection: false,
      idField: 'functionID',
      textField: 'functionName',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 5,
      allowSearchFilter: true
    };

  
  }
  create(){
    this.role = new Role();
  }
  update(role) {
    this.role = role;
  };
  delete(role) {
    this.role = role;
    this.roleservice.delete(role).subscribe(data => {
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
    this.roleservice.getAll().subscribe(data => {
      this.roles = data;
      this.dtTrigger.next();
    })
  }

  roleupdateform = new FormGroup({
    roleID: new FormControl(),
    roleName: new FormControl(),
    status: new FormControl(),
    description: new FormControl(),
    roleCode: new FormControl(),
    roleOrder: new FormControl(),
    function: new FormControl()
  });

  updateRole(role) {
    console.log(role);
    this.roleservice.save(role).subscribe(data => {
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
    this.dtTrigger.unsubscribe();
  }
  changeisUpdate() {
    this.isupdated = false;
  }

}
