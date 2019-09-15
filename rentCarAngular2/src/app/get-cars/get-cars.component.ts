import { Component, OnInit } from '@angular/core';
import { CarRepository } from '../services/CarRepository';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-cars',
  templateUrl: './get-cars.component.html',
  styleUrls: ['./get-cars.component.scss']
})
export class GetCarsComponent implements OnInit {

  cars: object;
  carId: number;

  constructor(private carRepository: CarRepository, private router: Router) { }

  ngOnInit() {
    this.carRepository.getCars().subscribe(cars => {
      this.cars = cars;
      console.log(cars);
    });
  }

  carDetail(id: number) {
    this.carId = id;
    console.log(this.carId);
    this.router.navigateByUrl('manage/carDetailView/' + this.carId);
  }

}
