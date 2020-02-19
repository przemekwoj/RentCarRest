import { Component, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationServiceService } from '../services/authentication-service.service';
import { AuthInfo } from '../auth-info';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  authInfo = new AuthInfo();

  hide = true;

  showPasswordError: boolean;
  showEmailError: boolean;
  wrongPasswordOrEmail: boolean;

  loginForm = new FormGroup({
    email: new FormControl('',[Validators.required, Validators.email]),
    password: new FormControl('',[Validators.required]),
    });
  constructor(private router: Router, 
     private authentcationService: AuthenticationServiceService) {

   }

  ngOnInit() {
  }

  get email() { return this.loginForm.get('email'); }

  get password() { return this.loginForm.get('password'); }

  emailInputFocus() {
    this.showEmailError = false;
    return this.showEmailError;
  }

  emailInputBlur() {
    this.showEmailError = this.email.invalid && (this.email.dirty || this.email.touched);
    return this.showEmailError;
  }

  passwordInputFocus() {
    this.showPasswordError = false;
    return this.showPasswordError;
  }

  passwordInputBlur() {
    this.showPasswordError = this.password.invalid && (this.password.dirty || this.password.touched);
    return this.showPasswordError;
  }
  checklogin() {
    this.authentcationService.getAuth(this.loginForm.value.email,this.loginForm.value.password).subscribe(
      auth => { 
              this.authInfo = auth as unknown as AuthInfo;
              console.log(this.authInfo);
              this.authentcationService.changeAuth(true);
              this.authentcationService.changeEmail(this.authInfo.email);
              this.authentcationService.changePassword(this.loginForm.value.password);
              this.authentcationService.changeRole(this.authInfo.role);
              this.loginForm.patchValue({
                email: '',
                password: ''
                })
              this.wrongPasswordOrEmail = false;
    },
    apiError => {
      console.log("error message :)");
      this.wrongPasswordOrEmail = true;
      }
    );
  }


}
