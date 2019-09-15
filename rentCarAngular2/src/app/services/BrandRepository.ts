import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Car } from '../car';
import { post } from 'selenium-webdriver/http';
import { Observable } from 'rxjs';
import { ReturnStatement } from '@angular/compiler';
import { Brand } from '../brand';

@Injectable({
  providedIn: 'root'
})
export class BrandRepository {

  constructor(private http: HttpClient) { }

  getBrands() {
    return this.http.get('http://localhost:8080/brand/brands');
  }

  postBrand(brand: Brand): Observable<Brand> {
    return this.http.post<Brand>('http://localhost:8080/brand/brand', brand);
  }
}
