import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {EmartCommonModule} from './emart-common/emart-common.module';
import {SellingModule} from './selling/selling.module';
import {ShoppingModule} from './shopping/shopping.module';
import {AdminModule} from './admin/admin.module';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { AuthHttpInterceptor } from './emart-common/auth/auth-http-interceptor';
import { ApiClientService } from './api-client.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    EmartCommonModule,
    NgbModule,
    SellingModule,
    ShoppingModule,
    AdminModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    ApiClientService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthHttpInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
