import { TestBed } from '@angular/core/testing';

import { AdministrationRepositoryService } from './administration-repository.service';

describe('AdministrationRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdministrationRepositoryService = TestBed.get(AdministrationRepositoryService);
    expect(service).toBeTruthy();
  });
});
