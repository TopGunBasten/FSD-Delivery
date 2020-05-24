import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {LogoutComponent} from './logout/logout.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ErrorComponent } from './error/error.component';
import { EmartAuthService } from './auth/emart-auth.service';
import { EmartCacheService } from './auth/emart-cache.service';
import { EmartUser } from './models/emart-user';



@NgModule({
  declarations:
  [ LoginComponent,
    LogoutComponent,
    ErrorComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule
  ]
})
export class EmartCommonModule { }
