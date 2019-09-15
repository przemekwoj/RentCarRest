import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ManageComponent } from './manage/manage.component';
import { GetCarsComponent } from './get-cars/get-cars.component';
import { PostCarComponent } from './post-car/post-car.component';
import { BrandComponent } from './brand/brand.component';
import { CarDetailsComponent } from './car-details/car-details.component';

const routes: Routes = [
  { path: 'manage', component: ManageComponent },
 /* { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },*/
  { path: 'manage/getCars', component: GetCarsComponent },
  { path: 'manage/postCar', component: PostCarComponent },
  { path: 'manage/createBrand', component: BrandComponent },
  { path: 'manage/carDetailView/:carId', component: CarDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [AppComponent, LoginComponent, ManageComponent,
  PostCarComponent, GetCarsComponent];
