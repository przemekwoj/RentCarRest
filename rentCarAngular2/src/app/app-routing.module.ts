import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { ManageComponent } from './manage/manage.component';
import { GetCarsComponent } from './get-cars/get-cars.component';
import { PostCarComponent } from './post-car/post-car.component';
import { BrandComponent } from './brand/brand.component';
import { CarDetailsComponent } from './car-details/car-details.component';
import { LoginComponent } from './login/login.component';
import { AddClientComponent } from './add-client/add-client.component';
import { OrderComponent } from './order/order.component';
import { AllOrdersComponent } from './all-orders/all-orders.component';
import { EmailComponent } from './email/email.component';

const routes: Routes = [
  { path: 'manage', component: ManageComponent },
  { path: 'login', redirectTo: '/', pathMatch: 'full' },
 // { path: 'login', component: LoginComponent},
  { path: 'manage/getCars', component: GetCarsComponent },
  { path: 'manage/postCar', component: PostCarComponent },
  { path: 'manage/createBrand', component: BrandComponent },
  { path: 'manage/carDetailView/:carId', component: CarDetailsComponent },
  { path: 'manage/addClinet', component: AddClientComponent },
  { path: 'manage/addOrder', component: OrderComponent },
  { path: 'manage/allOrders', component: AllOrdersComponent,runGuardsAndResolvers: 'always'},
  { path: 'manage/email', component: EmailComponent },

  //{ path: 'logout', component: LogoutComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{onSameUrlNavigation:'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [AppComponent, ManageComponent,
  PostCarComponent, GetCarsComponent];
