import { TestBed } from '@angular/core/testing';

import { EmartAuthService } from './emart-auth.service';

describe('EmartAuthService', () => {
  let service: EmartAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmartAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
