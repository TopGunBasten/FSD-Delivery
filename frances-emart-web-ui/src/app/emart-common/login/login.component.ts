import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth/auth-service-interface';
import { Router } from '@angular/router';
import { EmartAuthService } from '../auth/emart-auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [{ provide: 'AuthService', useClass: EmartAuthService }]
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
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
        if (user.isBuyer()) {
          this.router.navigate(['shopping/search']);
          return;
        }

        if (user.isSeller()) {
          this.router.navigate(['selling/items']);
          return;
        }
      },
      error => {
        console.log(error);
        if (error) { this.message = 'incorrect username or password.'; }
      }
    );
  }

  ngOnInit(): void {

  }

}
