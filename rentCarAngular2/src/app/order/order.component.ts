import { Component, OnInit } from '@angular/core';
import { ClientRepositoryService } from '../services/client-repository.service';
import { CarRepository } from '../services/CarRepository';
import { FormGroup, FormControl } from '@angular/forms';
import { Order } from '../order';
import { AdministrationRepositoryService } from '../services/administration-repository.service';
import { OrderRepositoryService } from '../services/order-repository.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  clients: object;
  cars: object;
  order: Order = new Order();
  employeeId: string;
  startDate = new Date();
  endDate = new Date(); 
  temporaryDate = new Date();

  newOrder = new FormGroup({
    carId: new FormControl(),
    clientId: new FormControl(),
  });

  constructor(private clientRepository: ClientRepositoryService,private carRepository: CarRepository,
              private administrationRepository: AdministrationRepositoryService,
              private orderRepositoryService: OrderRepositoryService) 
              { 
                this.endDate.setDate(this.startDate.getDate()+1);
                this.endDate.setHours(0,0,0,0)
                this.startDate.setHours(0,0,0,0)

              }

  ngOnInit() {
    this.getClients();
    this.getCars();
  }

  createNewOrder() {
    console.log(this.clients);
    console.log(this.cars);
    console.log(this.newOrder.value.clientId);
    console.log(this.newOrder.value.carId);
    console.log("new order");
    this.order.setCientId(this.newOrder.value.clientId);
    this.order.setcarId(this.newOrder.value.carId);
    this.order.setEndDate(this.endDate);
    this.order.setStartDate(this.startDate);
    console.log(this.order.startDate);
    console.log(this.order.endDate);
    this.orderRepositoryService.postOrder(this.order).subscribe(c =>
      { 
      console.log(c); 
      this.getClients();
      this.getCars(); 
    });
   // this.carRepository.postCarWithbrand(this.car, this.brandId).subscribe(c => { console.log(c); });
  }

  getCars() {
    this.carRepository.getAvailableCars().toPromise().then(cars => {
      this.cars = cars;
      console.log(cars);
    });
  }

  getClients() {
    this.clientRepository.getClients().toPromise().then(clients => {
      this.clients = clients;
      console.log(clients);
    });
  }

  subtractDatestartDate() {
    console.log('substract')
    this.startDate.setDate( this.startDate.getDate() - 1 ); 
    this.startDate = new Date(
      this.startDate.getFullYear(),
      this.startDate.getMonth() ,
      this.startDate.getDate()
    );
    console.log(this.startDate);
  }

  addDatestartDate() {
    console.log('add')
    this.temporaryDate = new Date(
      this.startDate.getFullYear(),
      this.startDate.getMonth() ,
      this.startDate.getDate()
    );
    this.temporaryDate.setDate( this.temporaryDate.getDate() + 1 ); 
    if(this.temporaryDate < this.endDate){
      this.startDate = this.temporaryDate;
    }
    console.log(this.startDate);
  }

  subtractDateEndDate() {
    console.log('substract')
    this.temporaryDate = new Date(
      this.endDate.getFullYear(),
      this.endDate.getMonth() ,
      this.endDate.getDate()
    );
    this.temporaryDate.setDate( this.temporaryDate.getDate() - 1 ); 
    if(this.temporaryDate > this.startDate){
      this.endDate = this.temporaryDate;
    }
    console.log(this.endDate);
  }

  addDateEndDate() {
    console.log('add')
    this.endDate.setDate( this.endDate.getDate() + 1 ); 
    this.endDate = new Date(
      this.endDate.getFullYear(),
      this.endDate.getMonth() ,
      this.endDate.getDate()
    );
    console.log(this.endDate);
  }
}
