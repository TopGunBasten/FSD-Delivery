import { TestBed } from '@angular/core/testing';

import { EmartCacheService } from './emart-cache.service';

describe('EmartCacheService', () => {
  let service: EmartCacheService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmartCacheService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
