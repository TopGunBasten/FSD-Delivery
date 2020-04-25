import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-buyer-signup',
  templateUrl: './buyer-signup.component.html',
  styleUrls: ['./buyer-signup.component.scss']
})
export class BuyerSignupComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  registerBuyer() {
    this.router.navigate(['shopping/search']);
  }


}
