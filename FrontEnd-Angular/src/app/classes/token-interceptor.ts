import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const userToken = localStorage.getItem("token");
        const modifiedReq = req.clone({ 
          headers: req.headers.set('Authorization', `${userToken}`),
        });
        return next.handle(modifiedReq);
      }
}
