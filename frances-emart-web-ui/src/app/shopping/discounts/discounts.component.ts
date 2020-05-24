import { Component, OnInit } from '@angular/core';
import { ApiClientService } from '../../api-client.service';
import { EmartCacheService } from '../../emart-common/auth/emart-cache.service';
import { EmartUser } from '../../emart-common/models/emart-user';

@Component({
  selector: 'app-discounts',
  templateUrl: './discounts.component.html',
  styleUrls: ['./discounts.component.scss']
})
export class DiscountsComponent implements OnInit {

  constructor(private apiService: ApiClientService,
              private cacheService: EmartCacheService
            )
            {}


  discounts: any = Array();

  ngOnInit(): void {
    this.apiService.getDiscounts(this.cacheService.getItem<EmartUser>('emart-user').id).subscribe(
      data => {
        this.discounts = data;
      }
    );
  }

}
