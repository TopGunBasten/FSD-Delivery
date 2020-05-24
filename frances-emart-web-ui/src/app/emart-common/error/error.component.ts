import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {

  errCode: number;
  errMessage: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.getErrInfo();
  }

  getErrInfo(): void {
    this.errCode = +this.route.snapshot.paramMap.get('errCode');
    this.errMessage = this.route.snapshot.paramMap.get('errMessage');
  }

}
