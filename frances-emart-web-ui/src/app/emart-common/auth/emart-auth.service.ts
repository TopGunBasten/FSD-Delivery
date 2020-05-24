import { Injectable } from '@angular/core';
import { AuthService } from './auth-service-interface';
import {EmartUser} from '../models/emart-user';
import { Observable } from 'rxjs';
import {ApiClientService} from '../../api-client.service';
import { EmartCacheService } from './emart-cache.service';


@Injectable({
  providedIn: 'root'
})
export class EmartAuthService implements AuthService {

  constructor(private apiService: ApiClientService, private cacheService: EmartCacheService) { }

  public getToken(): string {
    const currentUser = this.cacheService.getItem<EmartUser>('emart-user')
    return currentUser ? currentUser.jwtToken: null;
  }
  public clearToken() {
    this.cacheService.removeItem('emart-user');
  }

  public login(usename: string, password: string): Observable<EmartUser> {
    return new Observable<EmartUser>(
      observer => {
        this.apiService.authUser(usename, password).subscribe(data => {
          const user = JSON.parse(atob(data.token.split('.')[1]));
          const authUser = new EmartUser();
          authUser.id = user.jti;
          authUser.jwtToken = data.token;
          authUser.userName = user.sub;
          authUser.roles = user.roles;
          this.cacheService.setItem('emart-user', authUser);
          observer.next(authUser);
          observer.complete();
       }, err => observer.error(err));
    });
  }

  public logout() {
     this.apiService.signOff().subscribe(data => {
        this.cacheService.removeItem('emart-user');
     });
  }
}
