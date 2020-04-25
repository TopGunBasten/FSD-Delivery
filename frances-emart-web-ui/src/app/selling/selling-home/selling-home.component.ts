import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-selling-home',
  templateUrl: './selling-home.component.html',
  styleUrls: ['./selling-home.component.scss']
})
export class SellingHomeComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    if (this.router.url === '/selling'){
      this.router.navigate(['/selling/items']);
   }
  }

}
