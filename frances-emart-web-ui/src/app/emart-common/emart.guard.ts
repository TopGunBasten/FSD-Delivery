import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { EmartCacheService } from './auth/emart-cache.service';
import { EmartUser } from './models/emart-user';
import * as _ from 'underscore';

@Injectable({
  providedIn: 'root'
})
export class EmartGuard implements CanActivate {
  constructor(private cacheService: EmartCacheService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | boolean {
    const roles = next.data.roles;
    const currentUser = this.cacheService.getItem<EmartUser>('emart-user');
    if (!currentUser) {
     this.router.navigate(['login']);
     return false;
    }
    const actRoles  = _.intersection(roles, currentUser.roles);
    if (actRoles.length > 0){
      return true;
    }
    this.router.navigate(['error', { errCode: 403, errMessage: 'Not Allow to Access'}]);
    return false;
  }

}
