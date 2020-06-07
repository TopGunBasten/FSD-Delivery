import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './emart-common/login/login.component';
import { LogoutComponent } from './emart-common/logout/logout.component';
import { BuyerSignupComponent } from './shopping/buyer-signup/buyer-signup.component';
import { SellerSignupComponent } from './selling/seller-signup/seller-signup.component';
import { ShoppingHomeComponent } from './shopping/shopping-home/shopping-home.component';
import { SearchItemsComponent } from './shopping/search-items/search-items.component';
import { ShoppingCartComponent } from './shopping/shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './shopping/purchase-history/purchase-history.component';
import { DiscountsComponent } from './shopping/discounts/discounts.component';
import { SellingHomeComponent } from './selling/selling-home/selling-home.component';
import { ItemManagementComponent } from './selling/item-management/item-management.component';
import { SellingReportComponent } from './selling/selling-report/selling-report.component';
import { ErrorComponent } from './emart-common/error/error.component';
import { EmartGuard } from './emart-common/emart.guard';


const routes: Routes = [
  {path: 'error', component: ErrorComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent},
  {path: 'bSignup', component: BuyerSignupComponent},
  {path: 'sSignup', component: SellerSignupComponent},
  {path: '',   redirectTo: '/login', pathMatch: 'full' },
  {path: 'shopping', component: ShoppingHomeComponent,
     children: [
      {path: 'search', component: SearchItemsComponent,
      canActivate: [EmartGuard], data: {roles: ['BUYER']}},
      {path: 'cart', component: ShoppingCartComponent,
      canActivate: [EmartGuard], data: {roles: ['BUYER']}},
      {path: 'history', component: PurchaseHistoryComponent,
      canActivate: [EmartGuard], data: {roles: ['BUYER']}},
      {path: 'discounts', component: DiscountsComponent,
      canActivate: [EmartGuard], data: {roles: ['BUYER']}}
     ]
  },
  {path: 'selling', component: SellingHomeComponent,
     children: [
      {path: 'items', component: ItemManagementComponent,
      canActivate: [EmartGuard], data: {roles: ['SELLER']}},
      {path: 'report', component: SellingReportComponent,
      canActivate: [EmartGuard], data: {roles: ['SELLER']}}
     ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
