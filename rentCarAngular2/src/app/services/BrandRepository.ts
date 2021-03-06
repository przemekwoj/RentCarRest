import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Car } from '../car';
import { post } from 'selenium-webdriver/http';
import { Observable } from 'rxjs';
import { ReturnStatement } from '@angular/compiler';
import { Brand } from '../brand';
import { AuthenticationServiceService } from './authentication-service.service';

@Injectable({
  providedIn: 'root'
})
export class BrandRepository {

  email: string;
  password: string;

  constructor(private http: HttpClient,private authentcationService: AuthenticationServiceService) { }

  getBrands() {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.get('http://localhost:8080/brand/brands',{headers});
  }

  postBrand(brand: Brand): Observable<Brand> {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.post<Brand>('http://localhost:8080/brand/brand', brand,{headers});
  }

  setEmailAndPassword() {
    this.authentcationService.email.subscribe(email => this.email = email);
    this.authentcationService.password.subscribe(password => this.password = password)
    }
}
