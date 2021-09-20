import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GestoresService {

  constructor(
    private http: HttpClient
  ) { }

  create(form: any): Observable<any>{
    return this.http.post(`${environment.baseUrl}/manager`, form);
  }
}
