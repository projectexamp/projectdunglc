import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { User } from '../classes/user';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private user = new User();

  constructor(private loginService : LoginService, private router : Router) { }

  ngOnInit() {
    if((this.loginService.isLoggedIn()) )
    {
        this.router.navigate(['/home']);
    }
    else
    {
        this.router.navigate(['/login']);
    }
  }

  // create the form object.
  form = new FormGroup({
    userName : new FormControl('' , Validators.required),
    password : new FormControl('' , Validators.required)
  });

  Login(LoginInformation)
  {
      this.user.userName = this.userName.value;
      this.user.password = this.password.value;

      this.loginService.login(this.user).subscribe(
        result => {         
            if(result != null && result.token != null && result.userID != null )
            {
              localStorage.setItem("token" , result.token);
              localStorage.setItem("userID" , result.userID);
              localStorage.setItem("userName" , result.userName);
              this.router.navigate(['/home']);
            }
            else
            {
              alert("please register before login Or Invalid combination of Email and password");
            }
           
        },
        error => {
            console.log("Error in authentication");
        }
      );
  }

  get userName(){
      return this.form.get('userName');
  }

  get password(){
      return this.form.get('password');
  }

}
