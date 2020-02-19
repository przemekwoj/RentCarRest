import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule , routingComponents} from './app-routing.module';
import { AppComponent } from './app.component';
import { ManageComponent } from './manage/manage.component';
import { GetCarsComponent } from './get-cars/get-cars.component';
import { PostCarComponent } from './post-car/post-car.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrandComponent } from './brand/brand.component';
import { CarDetailsComponent } from './car-details/car-details.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AddClientComponent } from './add-client/add-client.component';
import { OrderComponent } from './order/order.component';
import { AllOrdersComponent } from './all-orders/all-orders.component';
import { InterceptorGlobalErrorHandlerService } from './services/interceptor-global-error-handler.service';
import {MatIconModule} from '@angular/material/icon';
import { MatFormFieldModule, MatInputModule } from '@angular/material';
@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    ManageComponent,
    GetCarsComponent,
    PostCarComponent,
    BrandComponent,
    CarDetailsComponent,
    LoginComponent,
    LogoutComponent,
    AddClientComponent,
    OrderComponent,
    AllOrdersComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorGlobalErrorHandlerService,
      multi: true
      }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
