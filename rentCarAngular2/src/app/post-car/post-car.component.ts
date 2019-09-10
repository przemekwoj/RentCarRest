import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../services/config.service';
import { Car } from '../car';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';


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
    available: new FormControl(''),
    brandId: new FormControl(''),
  });

  constructor(private data: ConfigService) {
    this.data.getBrands().subscribe(data => {
      this.brands = data;
      console.log(data);
    });
  }

  ngOnInit() {

  }

  async createCar() {
    //let 
    this.car.available = this.newCar.value.available;
    this.car.plateNumber = this.newCar.value.plateNumber;
    this.brandId = this.newCar.value.brandId;
    /*console.log('before post');
    this.data.postCar(this.car).subscribe(c => { console.log(c); } );
    console.log('after post');*/
    this.data.postCarWithrand(this.car, this.brandId).subscribe(c => { console.log(c); });
    //wait till save car in DB
    // await this.delay(500);
    //this.data.addCarToBrand(this.newCar.value.plateNumber, this.newCar.value.brandId).subscribe();
  }


  delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}
