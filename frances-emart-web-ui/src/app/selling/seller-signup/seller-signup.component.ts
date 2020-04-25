import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-seller-signup',
  templateUrl: './seller-signup.component.html',
  styleUrls: ['./seller-signup.component.scss']
})
export class SellerSignupComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  registerSeller() {
    this.router.navigate(['/selling/items']);
  }

}
