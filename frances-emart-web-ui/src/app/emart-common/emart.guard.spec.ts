import { TestBed } from '@angular/core/testing';

import { EmartGuard } from './emart.guard';

describe('EmartGuard', () => {
  let guard: EmartGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(EmartGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
