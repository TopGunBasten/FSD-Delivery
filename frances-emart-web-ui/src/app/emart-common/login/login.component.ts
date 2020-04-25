import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth-service-interface';
import { MockAuthServiceService } from '../auth/mock-auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [{provide: 'AuthService', useClass: MockAuthServiceService}]
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password : new FormControl('', [Validators.required])
  });

  private service: AuthService;

  message: string;

  get username() { return this.loginForm.get('username'); }

  get password() { return this.loginForm.get('password'); }

  constructor(@Inject('AuthService') authService: AuthService, private router: Router) {
     this.service = authService;
     console.log(this.message);
  }

  onSubmit() {
     this.service.login(this.username.value, this.password.value).subscribe(
       user => {
          if (user.userName === 'peter'){
            this.router.navigate(['shopping/search']);
          }
          if (user.userName === 'elly'){
            this.router.navigate(['selling/items']);
          }
       },
       error => {
         this.message = error.message;
       }
     );
  }

  ngOnInit(): void {

  }

}
