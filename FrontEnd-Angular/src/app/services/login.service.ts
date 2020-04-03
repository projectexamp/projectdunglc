import { Injectable } from '@angular/core';
import { Http, RequestOptions , Headers } from '@angular/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  // Base URL
  private  baseUrl = "http://localhost:8080/";

  

  constructor(private router : Router,private http :HttpClient) { }



  login(user : any) : Observable<any>
  {
      let url = this.baseUrl + "login";
      return this.http.post(url, user);
  }

  logout() 
  { 
    // Remove the token from the localStorage.
    localStorage.removeItem('token');
    localStorage.removeItem('userID');
    localStorage.removeItem('userName');    
    this.router.navigate(['']);

  }
  checkPass(user : any) : Observable<any>
  {
      let url = this.baseUrl + "checkPass";
      return this.http.post(url, user);
  }

  changePass(user : any) : Observable<any>
  {
      let url = this.baseUrl + "changePass";
      return this.http.post(url, user);
  }
  /*
	* Check whether User is loggedIn or not.
	*/

	isLoggedIn() { 

		// create an instance of JwtHelper class.
    let jwtHelper = new JwtHelperService();

		// get the token from the localStorage as we have to work on this token.
		let token = localStorage.getItem('token');

		// check whether if token have something or it is null.
		if(!token)
		{
			return false;
		}

		// get the Expiration date of the token by calling getTokenExpirationDate(String) method of JwtHelper class. this method accept a string value which is nothing but token.

		if(token)
		{
			let expirationDate = jwtHelper.getTokenExpirationDate(token);
			// check whether the token is expired or not by calling isTokenExpired() method of JwtHelper class.
			let isExpired = jwtHelper.isTokenExpired(token);

			return !isExpired;    
		}   
  }
  
  
  
}
