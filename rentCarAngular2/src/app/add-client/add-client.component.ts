import { Component, OnInit } from '@angular/core';
import { Client } from '../client';
import { FormGroup, FormControl } from '@angular/forms';
import { ClientRepositoryService } from '../services/client-repository.service';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.scss']
})
export class AddClientComponent implements OnInit {

  client: Client = new Client();

  newClient = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
    phone: new FormControl(''),
    password: new FormControl(''),
  });
  errorMsg: any = "ełłoł";
  constructor(private clientRepository: ClientRepositoryService) { }

  ngOnInit() {
  }

  addClient() {
    this.client.setFirstName(this.newClient.value.firstName);
    this.client.setPassword(this.newClient.value.password);
    this.client.setEmail(this.newClient.value.email);
    this.client.setPhone(this.newClient.value.phone);
    this.client.setlastName(this.newClient.value.lastName);
   // this.carRepository.postCarWithbrand(this.car, this.brandId).subscribe(c => { console.log(c); });
    this.clientRepository.postClient(this.client).subscribe(
     c => { console.log(c); },
     apiError => {this.errorMsg = apiError.message;});
     
    this.newClient.reset({
      firstName: '',
      password: '',
      email: '',
      phone: '',
      lastName: '',
    });
  }

}
