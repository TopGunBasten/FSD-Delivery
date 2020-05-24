import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ApiClientService } from '../../api-client.service';
import { EmartCacheService } from '../../emart-common/auth/emart-cache.service';
import { EmartUser } from '../../emart-common/models/emart-user';

@Component({
  selector: 'app-item-management',
  templateUrl: './item-management.component.html',
  styleUrls: ['./item-management.component.scss']
})
export class ItemManagementComponent implements OnInit {

  constructor(private modalService: NgbModal,
              private apiService: ApiClientService,
              private cacheService: EmartCacheService
             ) { }

  closeResult = '';

  currentPop: NgbModalRef;

  selectItem: any;

  items = new Array();

  ngOnInit(): void {
    this.apiService.getItems(this.cacheService.getItem<EmartUser>('emart-user').id).subscribe(
      data => {
        this.items = data;
      }
    );
  }

  subsribePop(item: any){
    this.items.push(item);
    this.currentPop.close();
  }

  subsribeUpdatePop(item: any){
    this.apiService.getItems(this.cacheService.getItem<EmartUser>('emart-user').id).subscribe(
      data => {
        this.items = data;
      }
    );
    this.currentPop.close();
  }

  open(content, item) {
    this.selectItem = item;

    this.currentPop = this.modalService.open(content,
       { size: 'lg',
        backdrop: 'static',
        ariaLabelledBy: 'modal-basic-title'
      });
    this.currentPop.result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
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
