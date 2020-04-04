import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationServiceService {

  private emailSource = new BehaviorSubject<string>('');
  email = this.emailSource.asObservable();
  private passwordSource = new BehaviorSubject<string>('');
  password = this.passwordSource.asObservable();
  private roleSource = new BehaviorSubject<string>('');
  role = this.roleSource.asObservable();
  private isAuthenticatedSource = new BehaviorSubject<boolean>(false);
  currentAuth = this.isAuthenticatedSource.asObservable();

  constructor(private router: Router, private http: HttpClient) { }

  getAuth(email,password) {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(email+':'+password) });
    return this.http.get('http://localhost:8080/basicauth',{headers});
  }

  getEmail(){
    return this.email;
  }
  changeAuth(isAuth: boolean) {
    this.isAuthenticatedSource.next(isAuth);
  }

  changePassword(pass: string) {
    this.passwordSource.next(pass);
  }

  changeEmail(mail: string) {
    this.emailSource.next(mail);
  }  
  
  changeRole(role: string) {
    this.roleSource.next(role);
  }

  
}
