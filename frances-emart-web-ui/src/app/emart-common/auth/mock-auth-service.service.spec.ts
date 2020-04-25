import { TestBed } from '@angular/core/testing';

import { MockAuthServiceService } from './mock-auth-service.service';

describe('MockAuthServiceService', () => {
  let service: MockAuthServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MockAuthServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should be a buyer', () => {
     service.login('elly', 'Test124').subscribe(user => {
       expect(user.userName).toEqual('elly');
       expect(user.password).toEqual('Test124');
     });
  });

  it('should be a seller', () => {
    service.login('peter', 'Test123').subscribe(user => {
      expect(user.userName).toEqual('peter');
      expect(user.password).toEqual('Test123');
    });
 });

});
