import { Injectable } from '@angular/core';
import { AuthService } from './auth-service-interface';
import { Observable, throwError, } from 'rxjs';
import { EmartUser } from '../models/emart-user';

@Injectable({
  providedIn: 'root'
})
export class MockAuthServiceService implements AuthService {

   private seller: EmartUser;
   private buyer: EmartUser;

   constructor() {
     this.initBuyer();
     this.initSeller();
   }
  getToken(): string {
    throw new Error("Method not implemented.");
  }
  clearToken() {
    throw new Error("Method not implemented.");
  }

  login(usename: string, password: string): Observable<EmartUser> {
    if (usename === this.seller.userName && password === this.seller.password) {
      return new Observable<EmartUser>(
        observer => {
          observer.next(this.seller);
          observer.complete();
        });
    }
    if (usename === this.buyer.userName && password === this.buyer.password) {
      return new Observable<EmartUser>(
        observer => {
          observer.next(this.buyer);
          observer.complete();
        });
    }

    return throwError(new Error('incorrect user or password'));

  }
  logout() {
    this.seller = null;
    this.buyer = null;
  }

  private initSeller() {
    this.seller = new EmartUser();
    this.seller.userName = 'peter';
    this.seller.password = 'Test123';
    this.seller.jwtToken = 'Seller';

  }

  private initBuyer() {
    this.buyer = new EmartUser();
    this.buyer.userName = 'elly';
    this.buyer.password = 'Test124';
    this.buyer.jwtToken = 'Buyer';
  }
}
