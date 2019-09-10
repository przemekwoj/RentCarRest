import { Component, Inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'rentCarAngular2';

  constructor(private router: Router) {
  }
  getCars() {
    console.log('get');
    window.location.replace('manage/getCars');
  }

  postCar() {
    console.log('post');
    window.location.replace('manage/postCar');
  }
  next() {
    console.log('next');
    console.log(this.router.url);
  }
}
