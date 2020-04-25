import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SellerSignupComponent } from './seller-signup/seller-signup.component';
import { SellingHomeComponent } from './selling-home/selling-home.component';
import { SellingHeaderComponent } from './selling-header/selling-header.component';
import { ItemManagementComponent } from './item-management/item-management.component';
import { NewItemComponent } from './new-item/new-item.component';
import { UpdateStockComponent } from './update-stock/update-stock.component';
import { UpdateItemComponent } from './update-item/update-item.component';
import { SellingReportComponent } from './selling-report/selling-report.component';
import { RouterModule } from '@angular/router';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    SellerSignupComponent,
     SellingHomeComponent,
     SellingHeaderComponent,
     ItemManagementComponent,
     NewItemComponent,
     UpdateStockComponent,
     UpdateItemComponent,
     SellingReportComponent],
  imports: [
    CommonModule,
    RouterModule,
    AngularEditorModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class SellingModule { }
