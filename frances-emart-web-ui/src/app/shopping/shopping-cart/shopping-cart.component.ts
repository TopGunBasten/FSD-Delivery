import { Component, OnInit } from '@angular/core';
import { ApiClientService } from '../../api-client.service';
import { EmartCacheService } from '../../emart-common/auth/emart-cache.service';
import { EmartUser } from '../../emart-common/models/emart-user';
import { ToastrService } from 'ngx-toastr';
import { EndOfLineState } from 'typescript';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss']
})
export class ShoppingCartComponent implements OnInit {

  constructor(private apiService: ApiClientService,
              private cacheService: EmartCacheService,
              private toastr: ToastrService,
              private router: Router,
  ) {

    this.cart = {};
    this.cart.items = {};
  }

  cart: any;

  total = 0;

  totalTax = 0;

  rate = 17;

  selectDiscount;

  currentPercent = 0;

  discounts = Array();

  computeTotal() {
    let itemTotal = 0;
    this.total = 0;
    this.totalTax = 0;
    // tslint:disable-next-line: forin
    for (const i in this.cart.items) {
      const lineTotal = this.cart.items[i].price * this.cart.items[i].quantity;
      const lineTax = lineTotal * this.rate / 100;
      const percent = (100 - this.currentPercent) / 100;
      (this.currentPercent === 0) ? this.totalTax += lineTax : this.totalTax += lineTax * percent;
      (this.currentPercent === 0) ? itemTotal += lineTotal : itemTotal += lineTotal * percent;
    }
    this.total = itemTotal + this.totalTax;
  }

  ngOnInit(): void {
    this.apiService.getCart(this.cacheService.getItem<EmartUser>('emart-user').id).subscribe(
      data => {
        this.cart = data;
        this.computeTotal();
      }
    );
    this.apiService.getActiveDiscounts(this.cacheService.getItem<EmartUser>('emart-user').id).subscribe(
      data => {
        this.discounts = data;
      }
    );
  }

  removeItem(item: any) {
    const carItem = {
      userId: this.cacheService.getItem<EmartUser>('emart-user').id,
      cartItem: item
    };
    this.apiService.removeItemFromCart(carItem).subscribe(
      data => {
        delete this.cart.items[item.id];
        this.toastr.success(`Item ${item.id.slice(24)} - ${item.name}`, 'has been remove from cart');
        this.computeTotal();
      }
    );
  }

  onChangeDiscount(event) {
    if (event.value === '') {
      this.currentPercent = 0;
      this.computeTotal();
      return;
    }
    this.discounts.forEach(discount => {
      if (discount.code.slice(24) === event.value) {
        this.selectDiscount = discount;
        this.currentPercent = this.selectDiscount.percent;
        this.computeTotal();
        return;
      }
    });
  }

  onCheckout() {
    const orderRequest = {
      buyerId: this.cacheService.getItem<EmartUser>('emart-user').id,
      discountCode: this.selectDiscount ? this.selectDiscount.code : '',
      discountPercentage: this.selectDiscount ? this.selectDiscount.percent : '',
      taxRate: 17,
      lines: []
    };

    // tslint:disable-next-line: forin
    for (const i in this.cart.items) {
      orderRequest.lines.push({
        itemId: this.cart.items[i].itemId,
        itemName: this.cart.items[i].itemName,
        price: this.cart.items[i].price,
        quantity: this.cart.items[i].quantity,
        thumbnail: this.cart.items[i].thumbnail
      });
    }

    this.apiService.placeOrder(orderRequest).subscribe(
      data => {
        this.toastr.success(`order # ${data.id}`, 'submit successful');
        this.apiService.cleanCart( this.cacheService.getItem<EmartUser>('emart-user').id);
        this.router.navigate(['shopping/search']);
      }
    );

  }


  onQuantityChange(event, id) {
    this.cart.items[id].quantity = event.target.value;
    this.total = 0;
    this.totalTax = 0;
    this.computeTotal();
    const cartItem = {
      userId: this.cacheService.getItem<EmartUser>('emart-user').id,
      cartItem: this.cart.items[id]
    };
    this.apiService.updateItemInCart(cartItem).subscribe(data => {

    }, error => {
      console.log(error);
    });
  }

}
