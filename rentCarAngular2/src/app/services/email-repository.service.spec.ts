import { TestBed } from '@angular/core/testing';

import { EmailRepositoryService } from './email-repository.service';

describe('EmailRepositoryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmailRepositoryService = TestBed.get(EmailRepositoryService);
    expect(service).toBeTruthy();
  });
});
