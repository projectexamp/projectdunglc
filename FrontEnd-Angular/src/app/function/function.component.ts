import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { Http } from '@angular/http';
import { FunctionService } from './function.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Function } from '../classes/function';
@Component({
  selector: 'app-function',
  templateUrl: './function.component.html',
  styleUrls: ['./function.component.css']
})
export class FunctionComponent implements OnDestroy, OnInit {
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  deleteMessage = false;
  isupdated = false;
  function: Function = new Function();
  functions: Function[] = [];
  constructor(private http: Http, private router: Router, private functionService: FunctionService, private toastr: ToastrService) { }
  

  ngOnInit(): void {

    this.getData();

    this.functionService.getAll().subscribe(data => {
      this.functions = data;
    })
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 2
    };
  
  }
  create(){
    this.function = new Function();
  }
  update(func) {
    this.function = func;
  };
  delete(func) {
    this.function = func;
    this.functionService.delete(this.function).subscribe(data => {
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
    this.functionService.getAll().subscribe(data => {
      this.functions = data;
      this.dtTrigger.next();
    })
  }

  functionupdateform = new FormGroup({
    functionID: new FormControl(),
    functionName: new FormControl(),
    status: new FormControl(),
    description: new FormControl(),
    functionCode: new FormControl(),
    functionOrder: new FormControl(),
    functionURL: new FormControl()
  });

  updatefunction(func) {
    this.function = func;
    console.log(this.function);
    this.functionService.save(this.function).subscribe(data => {
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
