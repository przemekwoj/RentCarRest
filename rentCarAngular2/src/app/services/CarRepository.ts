import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Car } from '../car';
import { post } from 'selenium-webdriver/http';
import { Observable } from 'rxjs';
import { ReturnStatement } from '@angular/compiler';
import { CarDetails } from '../carDetails';
import { AuthenticationServiceService } from './authentication-service.service';

@Injectable({
  providedIn: 'root'
})
export class CarRepository {
  email: string;
  password: string
  constructor(private http: HttpClient,private authentcationService: AuthenticationServiceService) { 


  }

  getCars() {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.get('http://localhost:8080/car/cars',{headers});
  }

  getAvailableCars() {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.get('http://localhost:8080/car/AvailableCars',{headers});
  }

  postCar(car: Car): Observable<Car> {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.post<Car>('http://localhost:8080/car/car', car,{headers});
  }

  postCarWithbrand(car: Car, brandId: number): Observable<any> {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    const url = 'http://localhost:8080/car/carWithBrand/' + brandId;
    return this.http.post<Car>(url, car,{headers});

  }

  getBrands() {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.get('http://localhost:8080/brand/brands',{headers});
  }

  addCarToBrand(plateNumber: string, brandId: number) {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    let url = 'http://localhost:8080/brand/brandId/' + brandId + '/plateNumber/' + plateNumber;
    return this.http.patch(url, null,{headers});
  }

  getCarDetail(carId: number){
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    let url = 'http://localhost:8080/car/carDetail/' + carId;
    return this.http.get(url,{headers});

  }

  putCarDetails(carDetail: CarDetails, carId: number): Observable<CarDetails>  {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    let url = 'http://localhost:8080/car/carDetail/' + carId;
    return this.http.put<CarDetails>(url, carDetail,{headers});
  }

  setEmailAndPassword() {
  this.authentcationService.email.subscribe(email => this.email = email);
  this.authentcationService.password.subscribe(password => this.password = password)
  }
}
