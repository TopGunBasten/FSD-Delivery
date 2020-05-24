import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiClientService {

  private options = {
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json'
    }
  };

  constructor(private httpClient: HttpClient) { }

  public authUser(username: string, password: string): Observable<any> {
    const body = {
      usernameOrEmail: username,
      password
    };

    return this.httpClient.post<any>('/identity/api/v1/auth/login', body, this.options);

  }

  public signOff(): Observable<any> {
    return this.httpClient.post<any>('/identity/api/v1//auth/logout', null, this.options);
  }

  public signUpAsBuyer(buyer: any): Observable<any> {
    return this.httpClient.post<any>('/identity/api/v1//users/buyer', buyer, this.options);
  }

  public signUpAsSeller(seller: any): Observable<any> {
    return this.httpClient.post<any>('/identity/api/v1//users/seller', seller, this.options);
  }

  public getCatagories(): Observable<any> {
    return this.httpClient.get<any>('/inventory/api/v1/catagories', this.options);
  }

  public getSubCatagories(id: string ): Observable<any> {
    return this.httpClient.get<any>(`/inventory/api/v1/catagories/subcatagories?catagoryId=${id}`, this.options);
  }

  public newItem(item: any): Observable<any> {
    return this.httpClient.post<any>('/inventory/api/v1/items', item, this.options);
  }

  public getItems(sellerId: string): Observable<any> {
    return this.httpClient.get<any>(`/inventory/api/v1/items/bySeller?sellerId=${sellerId}`);
  }

  public updateItem(itemProfile: any): Observable<any> {
    return this.httpClient.put<any>('/inventory/api/v1/items', itemProfile, this.options);
  }

  public updateStockNumber(itemStock: any): Observable<any> {
    return this.httpClient.put<any>('/inventory/api/v1/items/stock', itemStock, this.options);
  }

  public searchItems(text: string): Observable<any> {
    return this.httpClient.get<any>(`/inventory/api/v1/items/search?text=${text}`, this.options);
  }

  public getCart(userId: string): Observable<any> {
    return this.httpClient.get<any>(`/cart/api/v1/carts?userId=${userId}`, this.options);
  }

  public addItemToCart(item: any): Observable<any> {
    return this.httpClient.post<any>('/cart/api/v1/carts/items', item, this.options);
  }

  public updateItemInCart(item: any): Observable<any> {
    return this.httpClient.put<any>('/cart/api/v1/carts/items', item, this.options);
  }

  public removeItemFromCart(item: any): Observable<any> {
    return this.httpClient.post<any>('/cart/api/v1/carts/items/remove', item, this.options);
  }

  public cleanCart(id: string){
    return this.httpClient.delete<any>(`/cart/api/v1/carts/items/clean?userId=${id}`, this.options);
  }

  public getDiscounts(id: string): Observable<any> {
    return this.httpClient.get<any>(`/financial/api/v1//discounts/all?buyerId=${id}`, this.options);
  }


  public getActiveDiscounts(id: any): Observable<any> {
    return this.httpClient.get<any>(`/financial/api/v1/discounts/active?buyerId=${id}`, this.options);
  }

  public placeOrder(order: any): Observable<any> {
    return this.httpClient.post<any>('/order/api/v1/orders', order, this.options);
  }

  public getOrders(userId: string): Observable<any> {
    return this.httpClient.get<any>(`/order/api/v1/orders/byUser?userId=${userId}`, this.options);
  }


  public getSellerReport(filter: any): Observable<any> {
    return this.httpClient.post<any>('/financial/api/v1/transcations/seller', filter, this.options);
  }

}
