import { Component, OnInit } from '@angular/core';
import { CarRepository } from '../services/CarRepository';
import { Car } from '../car';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { BrandRepository } from '../services/BrandRepository';


@Component({
  selector: 'app-post-car',
  templateUrl: './post-car.component.html',
  styleUrls: ['./post-car.component.scss']
})
export class PostCarComponent implements OnInit {

  brands: object;
  car: Car = new Car();
  brandId: number;

  newCar = new FormGroup({
    plateNumber: new FormControl(''),
    available: new FormControl(true),
    brandId: new FormControl(''),
  });

  constructor(private carRepository: CarRepository, private brandRepository: BrandRepository) {
    this.brandRepository.getBrands().subscribe(brand => {
      this.brands = brand;
      console.log(brand);
    });
  }

  ngOnInit() {

  }

  async createCar() {
    this.car.available = this.newCar.value.available;
    this.car.plateNumber = this.newCar.value.plateNumber;
    this.brandId = this.newCar.value.brandId;
    this.carRepository.postCarWithbrand(this.car, this.brandId).subscribe(c => { console.log(c); });
    //wait till save car in DB
    // await this.delay(500);
    this.newCar.reset({
      plateNumber: '',
      available: '',
      brandId: '',
    });
  }


  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}
