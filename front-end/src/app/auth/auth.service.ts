import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) {}

  login(credentials: any): Observable<any>{
    return this.http.post(`${environment.baseUrl}/auth`, credentials);
  }

  logout(): void{
    localStorage.removeItem('access_token');
    localStorage.removeItem('name');
    window.location.href = '/login';
  }

  isLogged = ()=> !!localStorage.getItem('access_token');

  setToken = (token: string)=> localStorage.setItem('access_token', token);
  
  setName = (name: string)=> localStorage.setItem('name', name);
  
  getToken = ()=> localStorage.getItem('access_token');

  getName = ()=> localStorage.getItem('name');
}
