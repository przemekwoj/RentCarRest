import { Component, OnInit } from '@angular/core';
import { OrderRepositoryService } from '../services/order-repository.service';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrls: ['./all-orders.component.scss']
})
export class AllOrdersComponent implements OnInit {
  navigationSubscription;
  orders: object;

  constructor(private orderRepositoryService: OrderRepositoryService,private router: Router) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.loadOrders();      }
    });
  }



  ngOnInit() {
  }
  
  deleteOrder(orderId: number) {
    this.orderRepositoryService.deleteOrderById(orderId).subscribe(resp => {console.log(resp)});
    this.router.navigateByUrl('manage/allOrders');
  }

  loadOrders(){
    this.orderRepositoryService.getAllOrders().subscribe(orders => {
      this.orders = orders;
    });
  }

}
