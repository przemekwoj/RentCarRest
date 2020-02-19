import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { AuthenticationServiceService } from './authentication-service.service';
import { Client } from '../client';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ClientRepositoryService {
  email: string;
  password: string;
  constructor(private http: HttpClient,private authentcationService: AuthenticationServiceService) { }

  postClient(client: Client,): Observable<any> {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    const url = 'http://localhost:8080/client/client/';
    return this.http.post<Client>(url, client,{headers});
           //   .pipe(catchError(this.errorHandlerAddClient));
  }

  getClients() {
    this.setEmailAndPassword();
    console.log(this.email)
    console.log(this.password)
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.get('http://localhost:8080/client/clients',{headers});
  }
  setEmailAndPassword() {
    this.authentcationService.email.subscribe(email => this.email = email);
    this.authentcationService.password.subscribe(password => this.password = password)
    }

  /*  errorHandlerAddClient(errorHttp: HttpErrorResponse) {
      return throwError(errorHttp.error);
    }*/
}
