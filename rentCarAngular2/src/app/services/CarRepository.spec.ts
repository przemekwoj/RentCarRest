import { TestBed } from '@angular/core/testing';

import { CarRepository } from './CarRepository';

describe('CarRepository', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CarRepository = TestBed.get(CarRepository);
    expect(service).toBeTruthy();
  });
});
