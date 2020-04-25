import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Menu } from '../../emart-common/models/menu';

@Component({
  selector: 'app-shopping-header',
  templateUrl: './shopping-header.component.html',
  styleUrls: ['./shopping-header.component.scss']
})
export class ShoppingHeaderComponent implements OnInit {

  constructor(private router: Router) {

  }

  menus = {
    4: new Menu('Discounts', false, '/shopping/discounts'),
    3: new Menu('Purchase History', false, '/shopping/history'),
    2: new Menu('Shopping Cart', false, '/shopping/cart'),
    1: new Menu('Home', false, '/shopping/search'),
  };

  selectMenu: Menu;

  ngOnInit(): void {

    for (const key in this.menus) {
      if (this.menus[key].path === this.router.url) {
        this.selectMenu = this.menus[key];
        this.selectMenu.isActive = true;
        break;
      }
    }


  }

  active(key) {
    this.selectMenu.isActive = false;
    this.selectMenu = this.menus[key];
    this.selectMenu.isActive = true;
  }
}
