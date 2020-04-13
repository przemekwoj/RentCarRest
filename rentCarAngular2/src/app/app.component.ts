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
  selectedButton: string;

  constructor(private router: Router ,private authenticationService: AuthenticationServiceService) {
  }

  ngOnInit(): void {
    this.authenticationService.currentAuth.subscribe(isAuthenticated => this.isAuthenticated = isAuthenticated)
    console.log(" w app component is Auth " + this.isAuthenticated);
  }

  getCars() {
    console.log('get');
    this.selectedButton = 'getCars';
    this.router.navigateByUrl('manage/getCars');
  }

  postCar() {
    console.log('post');
    this.selectedButton = 'postCar';
    this.router.navigateByUrl('manage/postCar');
  }

  createBrand() {
    this.selectedButton = 'createBrand';
    this.router.navigateByUrl('manage/createBrand');
  }

  addClient() {
    this.selectedButton = 'addClient';
    this.router.navigateByUrl('manage/addClinet');
  }

  addOrder() {
    this.selectedButton = 'addOrder';
    this.router.navigateByUrl('manage/addOrder');

  }

  allOrders() {
    this.selectedButton = 'allOrders';
    this.router.navigateByUrl('manage/allOrders');
  }

  email(){
    this.selectedButton = 'email';
    this.router.navigateByUrl('manage/email');
  }

}
