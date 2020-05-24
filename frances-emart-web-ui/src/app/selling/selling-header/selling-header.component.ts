import { Component, OnInit } from '@angular/core';
import { Menu } from '../../emart-common/models/menu';
import { Router } from '@angular/router';
import { EmartCacheService } from 'src/app/emart-common/auth/emart-cache.service';
import { EmartUser } from '../../emart-common/models/emart-user';

@Component({
  selector: 'app-selling-header',
  templateUrl: './selling-header.component.html',
  styleUrls: ['./selling-header.component.scss']
})
export class SellingHeaderComponent implements OnInit {

  constructor(private router: Router, private cacheService: EmartCacheService ) { }


  menus = {
    2: new Menu('Report', false, '/selling/report'),
    1: new Menu('Item Management', false, '/selling/items'),
  };

  username: string;

  selectMenu: Menu;

  ngOnInit(): void {
     const user = this.cacheService.getItem<EmartUser>('emart-user');
     if (user) { this.username = user.userName; }

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
