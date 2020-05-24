import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, Validators, FormBuilder } from '@angular/forms';
import { ApiClientService } from '../../api-client.service';
import { EmartAuthService } from '../../emart-common/auth/emart-auth.service';
import { ConfirmedValidator } from '../../emart-common/validators/confirm-validator';

@Component({
  selector: 'app-buyer-signup',
  templateUrl: './buyer-signup.component.html',
  styleUrls: ['./buyer-signup.component.scss']
})
export class BuyerSignupComponent implements OnInit {

  constructor(private router: Router,
    private formBuilder: FormBuilder,
    private apiClient: ApiClientService,
    private authService: EmartAuthService
  ) { }

  get username() { return this.signUpForm.get('username'); }

  get password() { return this.signUpForm.get('password'); }

  get confirmPassword() { return this.signUpForm.get('confirmPassword'); }

  get phone() { return this.signUpForm.get('phone'); }

  get email() { return this.signUpForm.get('email'); }

  signUpForm = this.formBuilder.group({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email])
  }, { validator: ConfirmedValidator('password', 'confirmPassword') });

  errorMessage: string;

  ngOnInit(): void {
  }

  registerBuyer() {

    if (this.signUpForm.invalid) {
      return;
    }

    const buyer = {
      username: this.username.value,
      password: this.password.value,
      email: this.email.value,
      phone: this.phone.value
    };

    this.apiClient.signUpAsBuyer(buyer).subscribe(newBuyer => {
      this.authService.login(buyer.username, buyer.password).subscribe(data => {
        this.router.navigate(['shopping/search']);
      });
    }, error => {
      if (error) {
        console.log(error);
        this.errorMessage = 'username or email is existed.';
      }
    });

  }


}
