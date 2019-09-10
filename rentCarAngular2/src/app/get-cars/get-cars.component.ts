import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../services/config.service';

@Component({
  selector: 'app-get-cars',
  templateUrl: './get-cars.component.html',
  styleUrls: ['./get-cars.component.scss']
})
export class GetCarsComponent implements OnInit {

  cars: object;

  constructor(private data: ConfigService) { }

  ngOnInit() {
    this.data.getCars().subscribe(data => {
      this.cars = data;
      console.log(data);
    });
  }

}
