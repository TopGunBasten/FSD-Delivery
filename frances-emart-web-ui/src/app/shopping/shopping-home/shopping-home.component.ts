import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shopping-home',
  templateUrl: './shopping-home.component.html',
  styleUrls: ['./shopping-home.component.scss']
})
export class ShoppingHomeComponent implements OnInit {

  constructor(private router: Router) {
     if (this.router.url === '/shopping'){
        this.router.navigate(['/shopping/search']);
     }
   }

  ngOnInit(): void {
  }

}
