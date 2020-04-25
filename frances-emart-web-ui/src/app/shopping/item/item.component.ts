import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss'],
  providers: [NgbCarouselConfig]
})
export class ItemComponent implements OnInit {

  constructor(config: NgbCarouselConfig) { 
    config.interval = 10000;
    config.wrap = false;
    config.keyboard = false;
    config.pauseOnHover = false;
  }

  images = [700, 533, 807, 124].map((n) => `https://picsum.photos/id/${n}/900/500`);

  ngOnInit(): void {

  }

}
