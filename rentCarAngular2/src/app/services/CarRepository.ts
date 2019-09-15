import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Car } from '../car';
import { post } from 'selenium-webdriver/http';
import { Observable } from 'rxjs';
import { ReturnStatement } from '@angular/compiler';
import { CarDetails } from '../carDetails';

@Injectable({
  providedIn: 'root'
})
export class CarRepository {

  constructor(private http: HttpClient) { }

  getCars() {
    return this.http.get('http://localhost:8080/car/cars');
  }

  postCar(car: Car): Observable<Car> {
    return this.http.post<Car>('http://localhost:8080/car/car', car);
  }

  postCarWithbrand(car: Car, brandId: number): Observable<any> {
    const url = 'http://localhost:8080/car/carWithBrand/' + brandId;
    return this.http.post<Car>(url, car);

  }

  getBrands() {
    return this.http.get('http://localhost:8080/brand/brands');
  }

  addCarToBrand(plateNumber: string, brandId: number) {
    let url = 'http://localhost:8080/brand/brandId/' + brandId + '/plateNumber/' + plateNumber;
    return this.http.patch(url, null);
  }

  getCarDetail(carId: number){
    let url = 'http://localhost:8080/car/carDetail/' + carId;
    return this.http.get(url);

  }

  putCarDetails(carDetail: CarDetails, carId: number): Observable<CarDetails>  {
    let url = 'http://localhost:8080/car/carDetail/' + carId;
    return this.http.put<CarDetails>(url, carDetail);
  }

}
