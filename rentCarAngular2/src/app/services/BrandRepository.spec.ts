import { TestBed } from '@angular/core/testing';

import { BrandRepository } from './BrandRepository';

describe('BrandRepository', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BrandRepository = TestBed.get(BrandRepository);
    expect(service).toBeTruthy();
  });
});
