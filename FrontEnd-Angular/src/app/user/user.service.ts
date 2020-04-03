import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UserService {

 
  private baseUrl = 'http://localhost:8080/user/';  
  
  constructor(private http:HttpClient) { }  
  
  getAll(): Observable<any> {  
    return this.http.get(`${this.baseUrl}`+'getAll');  
  }  
  
  save(user: object): Observable<any> {  
    return this.http.post(`${this.baseUrl}`+'save', user);  
  }  
  
  delete(user: object): Observable<any> {  
    return this.http.post(`${this.baseUrl}`+`delete`, user);  
  }  

  getCurrentFunction(token): Observable<any> {  
    return this.http.post(`${this.baseUrl}`+`getCurrentFunction`, token);  
  }  
  
}
