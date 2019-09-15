import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CarRepository } from '../services/CarRepository';
import { FormGroup, FormControl } from '@angular/forms';
import { CarDetails } from '../carDetails';
import { CombineLatestOperator } from 'rxjs/internal/observable/combineLatest';
import { CloneVisitor } from '@angular/compiler/src/i18n/i18n_ast';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.scss']
})
export class CarDetailsComponent implements OnInit {

  carId: number;
  carDetails: CarDetails;
  editable: boolean;
  btn: string = 'edit';
  btnId: string = 'btnEdit';
  ishidden: boolean;

  newCarDetails = new FormGroup({
    color: new FormControl(),
    weight: new FormControl(),
    hight: new FormControl(),
  });

  constructor(private route: ActivatedRoute, private carRepository: CarRepository) {
    this.newCarDetails.controls.color.disable();
    this.newCarDetails.controls.weight.disable();
    this.newCarDetails.controls.hight.disable();
  }

  ngOnInit() {
    this.carId = this.route.snapshot.params['carId'];
    this.carRepository.getCarDetail(this.carId).subscribe
    (carDetails => {
      this.carDetails = new CarDetails(carDetails);
      console.log(this.carDetails);
      this.newCarDetails.setValue({color: this.carDetails.color,
                                  weight: this.carDetails.weight,
                                  hight : this.carDetails.hight});
    });
  }

  createNewCarDetails() {
    this.carDetails.color = this.newCarDetails.value.color;
    this.carDetails.hight = this.newCarDetails.value.hight;
    this.carDetails.weight = this.newCarDetails.value.weight;
    console.log(this.carDetails);
    this.carRepository.putCarDetails(this.carDetails, this.carId).subscribe(cd => { console.log(cd); });
    this.newCarDetails.controls.color.disable();
    this.newCarDetails.controls.weight.disable();
    this.newCarDetails.controls.hight.disable();
    this.ishidden = false;



  }

  enableEdit() {
    console.log('enableEdite()')
    this.newCarDetails.controls.color.enable();
    this.newCarDetails.controls.weight.enable();
    this.newCarDetails.controls.hight.enable();
    this.ishidden = true;

  }

}
