import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FunctionService {

 
  private baseUrl = 'http://localhost:8080/function/';  
  
  constructor(private http:HttpClient) { }  
  
  getAll(): Observable<any> {  
    return this.http.get(`${this.baseUrl}`+'getAll');  
  }  
  
  save(role: object): Observable<any> {  
    return this.http.post(`${this.baseUrl}`+'save', role);  
  }  
  
  delete(role: object): Observable<any> {  
    return this.http.post(`${this.baseUrl}`+`delete`, role);  
  }  
  getCurrentFunction(token): Observable<any> {  
    return this.http.post(`${this.baseUrl}`+`getCurrentFunction`, token);  
  }  
  
  // getStudent(id: number): Observable<Object> {  
  //   return this.http.get(`${this.baseUrl}/student/${id}`);  
  // }  
  
  // updateStudent(id: number, value: any): Observable<Object> {  
  //   return this.http.post(`${this.baseUrl}/update-student/${id}`, value);  
  // }  
}
