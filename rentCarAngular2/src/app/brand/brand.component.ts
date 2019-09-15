import { Component, OnInit } from '@angular/core';
import { Brand } from '../brand';
import { FormGroup, FormControl } from '@angular/forms';
import { BrandRepository } from '../services/BrandRepository';

@Component({
  selector: 'app-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.scss']
})
export class BrandComponent implements OnInit {

  brand: Brand = new Brand();

  newBrand = new FormGroup({
    brandName: new FormControl(''),
  });

  constructor(private brandRepository: BrandRepository) { }

  ngOnInit() {
  }

  createBrand()
  {
    this.brand.brandName = this.newBrand.value.brandName;
    this.brandRepository.postBrand(this.brand).subscribe(b => { console.log(b); });

    this.newBrand.reset({
      brandName: '',
    });
  }
}
