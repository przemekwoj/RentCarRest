import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationServiceService } from './authentication-service.service';
import { Order } from '../order';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderRepositoryService {

  email: string;
  password: string;

  constructor(private http: HttpClient, private authentcationService: AuthenticationServiceService) { }

  postOrder(order: Order): Observable<Order> {
    this.setEmailAndPassword();
    order.setEmployeeMail(this.email);
    const url = 'http://localhost:8080/order/createNewOrder';
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.post<Order>(url, order,{headers});
  }

  getAllOrders() {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.get('http://localhost:8080/order/orders',{headers});
  }

  deleteOrderById(orderId: number){
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    const url = 'http://localhost:8080/order/delete/'+orderId;
    console.log(url);
    return this.http.delete(url,{headers});

  }

  setEmailAndPassword() {
    this.authentcationService.email.subscribe(email => this.email = email);
    this.authentcationService.password.subscribe(password => this.password = password)
    }
}
