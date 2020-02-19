import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationServiceService } from './authentication-service.service';

@Injectable({
  providedIn: 'root'
})
export class AdministrationRepositoryService {

  email: string;
  password: string;
  constructor(private http: HttpClient,private authentcationService: AuthenticationServiceService) { }

  getIdByMail(mail:string) {
    const url = 'http://localhost:8080/administration/getIdByMail/'+mail;
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    return this.http.get(url,{headers});
  }

  setEmailAndPassword() {
    this.authentcationService.email.subscribe(email => this.email = email);
    this.authentcationService.password.subscribe(password => this.password = password)
    }
}
