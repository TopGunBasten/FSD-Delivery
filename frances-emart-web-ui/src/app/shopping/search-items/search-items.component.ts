import { Component, OnInit } from '@angular/core';
import {NgbModal, ModalDismissReasons, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, Validators, FormControl } from '@angular/forms';
import { ApiClientService } from '../../api-client.service';
import * as _ from 'underscore';
import { Observable } from 'rxjs';
import { EmartCacheService } from '../../emart-common/auth/emart-cache.service';
import { EmartUser } from '../../emart-common/models/emart-user';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-items',
  templateUrl: './search-items.component.html',
  styleUrls: ['./search-items.component.scss']
})
export class SearchItemsComponent implements OnInit {

  constructor(private modalService: NgbModal,
              private formBuilder: FormBuilder,
              private apiService: ApiClientService,
              private cacheService: EmartCacheService,
              private toastr: ToastrService,
              private router: Router) { }

  get searchText(){ return this.searchForm.get('searchText'); }

  items = Array();
  totalItems = Array();

  selectItem;

  searchForm = this.formBuilder.group({
    searchText: new FormControl('', [Validators.required])
  });

  closeResult = '';


  manufacturers = Array();

  currentMinPrice: any;
  currentMaxPrice: any;
  currentManufacturer: string;

  currentModel: NgbModalRef;

  ngOnInit(): void {
  }

  open(content, item) {
    this.currentModel = this.modalService.open(content, { size: 'lg', backdrop: 'static', ariaLabelledBy: 'modal-basic-title'});
    this.currentModel.result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
    this.selectItem = item;
  }

  onMaxPriceChange(maxPrice) {
     if (this.currentManufacturer === '' &&
        this.currentMinPrice === ''
        && maxPrice === '')
        {
          this.items = this.totalItems;
        }
     if (maxPrice === '') { return; }
     this.items = _.filter(this.items, item => item.price <= maxPrice);
     this.currentMaxPrice = maxPrice;
  }

  onMinPriceChange(minPrice) {
    if (this.currentManufacturer === '' &&
    this.currentMaxPrice === ''
    && minPrice === '')
    {
      this.items = this.totalItems;
    }
    if (minPrice === '') { return; }
    this.items = _.filter(this.items, item => item.price >= minPrice);
    this.currentMinPrice = minPrice;
  }

  onManufacturerChange(m) {
    if (this.currentMinPrice === '' &&
    this.currentMaxPrice === ''
    && m === '')
    {
      this.items = this.totalItems;
    }
    if (m === '') { return; }
    this.items = _.where(this.items, {manufacturer: m});
    this.currentManufacturer = m;
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  onSubmit(){
    if (this.searchForm.invalid) { return; }
    this.apiService.searchItems(this.searchText.value).subscribe(
      data => {
        this.items = data;
        this.totalItems = data;
        this.manufacturers = _.uniq(_.pluck(this.items, 'manufacturer'));
      }, error => {
        console.log(error);
      }
    );
  }

  onAddItemToChart(item) {
    this.addItemToCart(item).subscribe(data =>
      {
        this.currentModel.close();
        this.toastr.success(`Item ${item.id.slice(24)} - ${item.name}`, 'has been add to cart');
      });
  }

  onCheckOut(item){
    this.addItemToCart(item).subscribe(data =>
      {
        this.currentModel.close();
        this.router.navigate(['shopping/cart']);
      });
  }

  private addItemToCart(item: any): Observable<any> {
    const carItem = {
      userId: this.cacheService.getItem<EmartUser>('emart-user').id,
      cartItem: {
         itemId: item.id,
         itemName: item.name,
         price: item.price,
         thumbnail: item.thumbnail,
         quantity: 1
      }
    };
    return this.apiService.addItemToCart(carItem);
  }

}
