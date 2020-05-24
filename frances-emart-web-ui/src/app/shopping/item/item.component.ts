import { Component, OnInit, Input } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss'],
  providers: [NgbCarouselConfig]
})
export class ItemComponent implements OnInit {

  constructor(config: NgbCarouselConfig) {
    config.interval = 5000;
    config.wrap = false;
    config.keyboard = false;
    config.pauseOnHover = false;
  }

  @Input() item: any;

  images = Array();


  ngOnInit(): void {
    this.images.push(this.item.detailImage1);
    this.images.push(this.item.detailImage2);
    this.images.push(this.item.detailImage3);
    this.images.push(this.item.detailImage4);
    this.images.push(this.item.detailImage5);
  }

}
