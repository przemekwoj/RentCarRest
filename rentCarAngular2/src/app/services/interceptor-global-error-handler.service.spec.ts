import { TestBed } from '@angular/core/testing';

import { InterceptorGlobalErrorHandlerService } from './interceptor-global-error-handler.service';

describe('InterceptorGlobalErrorHandlerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InterceptorGlobalErrorHandlerService = TestBed.get(InterceptorGlobalErrorHandlerService);
    expect(service).toBeTruthy();
  });
});
