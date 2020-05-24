import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, Validators, AbstractControl, FormBuilder } from '@angular/forms';
import { ConfirmedValidator } from '../../emart-common/validators/confirm-validator';
import { ApiClientService } from 'src/app/api-client.service';
import { EmartAuthService } from 'src/app/emart-common/auth/emart-auth.service';

@Component({
  selector: 'app-seller-signup',
  templateUrl: './seller-signup.component.html',
  styleUrls: ['./seller-signup.component.scss']
})
export class SellerSignupComponent implements OnInit {

  constructor(private router: Router,
              private formBuilder: FormBuilder,
              private apiClient: ApiClientService,
              private authService: EmartAuthService)
     { }

get username() { return this.signUpForm.get('username'); }

get gstin() { return this.signUpForm.get('gstin'); }

get password() { return this.signUpForm.get('password'); }

get brief() { return this.signUpForm.get('brief'); }

get confirmPassword() { return this.signUpForm.get('confirmPassword'); }

get postalAddress() { return this.signUpForm.get('postalAddress'); }

get companyName() { return this.signUpForm.get('companyName'); }

get contactNumber() { return this.signUpForm.get('contactNumber'); }

get webSite() { return this.signUpForm.get('webSite'); }

get email() { return this.signUpForm.get('email'); }

signUpForm = this.formBuilder.group({
    username: new FormControl('', [Validators.required]),
    gstin: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    brief: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
    postalAddress: new FormControl('', [Validators.required]),
    companyName: new FormControl('', [Validators.required]),
    contactNumber:  new FormControl('', [Validators.required]),
    webSite: new FormControl('', [Validators.required, Validators.pattern('(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?')]),
    email: new FormControl('', [Validators.required, Validators.email])
  }, {validator: ConfirmedValidator('password', 'confirmPassword')});

errorMessage: string;

ngOnInit(): void {
}

registerSeller() {

  if (this.signUpForm.invalid) {
    return;
  }

  const seller = {
    username: this.username.value,
    password: this.password.value,
    email: this.email.value,
    companyName: this.companyName.value,
    gstin: this.gstin.value,
    brief: this.brief.value,
    postalAddress: this.postalAddress.value,
    webSite: this.webSite.value,
    contractNumber: this.contactNumber.value
  };



  this.apiClient.signUpAsSeller(seller).subscribe(newSeller => {
    this.authService.login(seller.username, seller.password).subscribe(data => {
      this.router.navigate(['/selling/items']);
    });
  }, error => {
    if (error){
      console.log(error);
      this.errorMessage = 'username or email is existed.';
    }
  });

  }

}
