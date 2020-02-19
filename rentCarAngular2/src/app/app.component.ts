import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from './services/authentication-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {



  title = 'rentCarAngular2';
  isAuthenticated: boolean;

  constructor(private router: Router ,private authenticationService: AuthenticationServiceService) {
  }

  ngOnInit(): void {
    this.authenticationService.currentAuth.subscribe(isAuthenticated => this.isAuthenticated = isAuthenticated)
    console.log(" w app component is Auth " + this.isAuthenticated);
  }

  getCars() {
    console.log('get');
    this.router.navigateByUrl('manage/getCars');
  }

  postCar() {
    console.log('post');
    this.router.navigateByUrl('manage/postCar');
  }

  createBrand() {
    this.router.navigateByUrl('manage/createBrand');
  }

  addClient() {
    this.router.navigateByUrl('manage/addClinet');
  }

  addOrder() {
    this.router.navigateByUrl('manage/addOrder');

  }

  allOrders() {
    this.router.navigateByUrl('manage/allOrders');

  }
}
