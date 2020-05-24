import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ApiClientService } from '../../api-client.service';
import { EmartCacheService } from '../../emart-common/auth/emart-cache.service';
import { EmartUser } from 'src/app/emart-common/models/emart-user';

@Component({
  selector: 'app-selling-report',
  templateUrl: './selling-report.component.html',
  styleUrls: ['./selling-report.component.scss']
})
export class SellingReportComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private apiService: ApiClientService,
              private cacheService: EmartCacheService
    ) { }

  get startDate() { return this.reportForm.get('startDate'); }
  get endDate() { return this.reportForm.get('endDate'); }

  reportForm = this.formBuilder.group({
    startDate: new FormControl('',  [Validators.required]),
    endDate: new FormControl('',  [Validators.required])
  });

  trans = Array();

  ngOnInit(): void {
  }

  onSubmit(){
    if (this.reportForm.invalid) { return; }

    const filter = {
      startDate: this.startDate.value,
      endDate: this.endDate.value,
      buyerId: this.cacheService.getItem<EmartUser>('emart-user').id
    };
    this.apiService.getSellerReport(filter).subscribe(
      data => {
      this.trans = data;
      }
    );
  }

}
