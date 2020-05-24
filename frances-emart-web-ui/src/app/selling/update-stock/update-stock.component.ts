import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ApiClientService } from '../../api-client.service';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-update-stock',
  templateUrl: './update-stock.component.html',
  styleUrls: ['./update-stock.component.scss']
})
export class UpdateStockComponent implements OnInit {

  @Input() item: any;

  @Output() closeStockPop: EventEmitter<any> = new EventEmitter();

  constructor(private apiService: ApiClientService,
              private formBuilder: FormBuilder) { }

  stockForm = null;

  errorMessage: string;

  get stockNumber() { return this.stockForm.get('stockNumber'); }

  ngOnInit(): void {
    this.stockForm = this.formBuilder.group({
      stockNumber: new FormControl(this.item.stockNumber, [Validators.required, Validators.min(0)])
    });
  }

  onSubmit(): void {
    if (this.stockForm.invalid) { return; }

    const item = {
      id: this.item.id,
      stockNumber: this.stockNumber.value
    };

    this.apiService.updateStockNumber(item).subscribe(updateItem => {
      this.closeStockPop.emit(updateItem);
    }, error => {
      if (error) {
        this.errorMessage = error.message;
      }
    });

  }

}
