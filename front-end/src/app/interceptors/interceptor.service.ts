import { Injectable } from '@angular/core';
import { 
  HttpInterceptor,
  HttpHandler,
  HttpRequest, 
  HttpResponse,
  HttpErrorResponse}
from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../auth/auth.service';

import { tap, catchError } from "rxjs/operators";
import { ToastrService } from 'ngx-toastr';


@Injectable()
export class Interceptor implements HttpInterceptor {

    constructor(
        private authService: AuthService,
        private toastrService: ToastrService){}

    intercept( request: HttpRequest<any>, next: HttpHandler ): Observable<any> {
        console.log('as')
        if(this.authService.isLogged()){
            request = request.clone({
                setHeaders: {
                    Authorization: `Bearer ${this.authService.getToken()}`
                },
                
            });
        }
        return next.handle(request).pipe(
            tap((evt) => {
                if (evt instanceof HttpResponse) {
                    this.toastrService.success('Sucesso!', 'Requisição efetuada com sucesso!')
                }
            }),
            catchError((err: any) => {
                if(err instanceof HttpErrorResponse) {
                    try {
                        if(err.status && err.status == 401 || err.status && err.status == 403){
                            this.authService.logout();
                        } else {
                            this.toastrService.error('Erro!',err.error.message)
                        }
                    } catch(e) {}
                }
                return err;
            })
        );
    }
}
