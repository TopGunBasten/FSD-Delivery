import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ApiClientService } from 'src/app/api-client.service';
import { EmartCacheService } from 'src/app/emart-common/auth/emart-cache.service';
import { EmartUser } from 'src/app/emart-common/models/emart-user';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.scss']
})
export class PurchaseHistoryComponent implements OnInit {

  constructor(private modalService: NgbModal,
              private apiService: ApiClientService,
              private cacheService: EmartCacheService) { }

  orders = Array();

  closeResult = '';

  currentOrder = {};
  ngOnInit(): void {
    this.apiService.getOrders(this.cacheService.getItem<EmartUser>('emart-user').id).subscribe(
      data => {
        this.orders = data;
        this.orders.forEach(order => {
            this.computeOrderTotal(order);
        });
      }
    );
  }

  computeOrderTotal(order) {
    let itemTotal = 0;
    order.total = 0;
    order.totalTax = 0;
    // tslint:disable-next-line: forin
    for (const i in order.lines) {
      const lineTotal = order.lines[i].price * order.lines[i].quantity;
      const lineTax = lineTotal * order.taxRate / 100;
      let percent = 0;
      if (order.discountCode !== '')
      {
         percent = (100 - order.discountPercentage) / 100;
      }
      (percent === 0) ? order.totalTax += lineTax : order.totalTax += lineTax * percent;
      (percent === 0) ? itemTotal += lineTotal : itemTotal += lineTotal * percent;
    }
    order.total = itemTotal + order.totalTax;
  }

  open(content, order) {
    this.modalService.open(content, { size: 'lg', backdrop: 'static', ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
    this.currentOrder = order;
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

}
