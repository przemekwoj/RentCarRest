import { Injectable } from '@angular/core';
import { EmailSend } from '../email-send';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationServiceService } from './authentication-service.service';
import { Observable } from 'rxjs';
import { Client } from '../client';
import { QueueType } from '../queue-type.enum';


@Injectable({
  providedIn: 'root'
})
export class EmailRepositoryService {
  email: string;
  password: string;

  constructor(private http: HttpClient ,private authentcationService: AuthenticationServiceService) { }

  postEmail(emailSend: EmailSend): Observable<any> {
    this.setEmailAndPassword();
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.email+':'+this.password) });
    const url = 'http://localhost:8080/email/sendEmail';
    return this.http.post<Client>(url, emailSend,{headers});
           //   .pipe(catchError(this.errorHandlerAddClient));
  }

  setEmailAndPassword() {
    this.authentcationService.email.subscribe(email => this.email = email);
    this.authentcationService.password.subscribe(password => this.password = password)
    }
}
