import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {LogoutComponent} from './logout/logout.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations:
  [ LoginComponent,
    LogoutComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule
  ]
})
export class EmartCommonModule { }