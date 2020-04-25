import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BuyerSignupComponent } from './buyer-signup/buyer-signup.component';
import { ShoppingHomeComponent } from './shopping-home/shopping-home.component';
import { ShoppingHeaderComponent } from './shopping-header/shopping-header.component';
import { SearchItemsComponent } from './search-items/search-items.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './purchase-history/purchase-history.component';
import { ItemComponent } from './item/item.component';
import { DiscountsComponent } from './discounts/discounts.component';
import { RouterModule } from '@angular/router';
import {NgbCarouselModule} from '@ng-bootstrap/ng-bootstrap';
import { OrderComponent } from './order/order.component';
import { EmartCommonModule } from '../emart-common/emart-common.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    BuyerSignupComponent,
    ShoppingHomeComponent,
    ShoppingHeaderComponent,
    SearchItemsComponent,
    ShoppingCartComponent,
    PurchaseHistoryComponent,
    ItemComponent,
    DiscountsComponent,
    OrderComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    NgbCarouselModule,
    EmartCommonModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class ShoppingModule { }
