import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VeiculosService {

  constructor(
    private http: HttpClient
  ) { }

  create(form: any): Observable<any>{
    return this.http.post(`${environment.baseUrl}/veiculos`, form);
  }

  update(id: any,form: any): Observable<any>{
    return this.http.put(`${environment.baseUrl}/veiculos/${id}`, form);
  }

  toSale(id: number,vendido: any): Promise<any>{
    return this.http.patch(`${environment.baseUrl}/veiculos/${id}/${vendido}`, {}).toPromise();
  }

  delete(id: number): Observable<any>{
    return this.http.delete(`${environment.baseUrl}/veiculos/${id}`);
  }

  findAll(): Observable<any>{
    return this.http.get(`${environment.baseUrl}/veiculos`);
  }

  findById(id: any): Observable<any>{
    return this.http.get(`${environment.baseUrl}/veiculos/${id}`);
  }
}
