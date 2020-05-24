import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ApiClientService } from '../../api-client.service';
import { EmartCacheService } from '../../emart-common/auth/emart-cache.service';
import { EmartUser } from '../../emart-common/models/emart-user';





@Component({
  selector: 'app-new-item',
  templateUrl: './new-item.component.html',
  styleUrls: ['./new-item.component.scss']
})
export class NewItemComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private apiService: ApiClientService,
              private cacheService: EmartCacheService
  ) { }

  get catagory() { return this.newItemForm.get('catagory'); }
  get subCatagory() { return this.newItemForm.get('subCatagory'); }
  get name() { return this.newItemForm.get('name'); }
  get manufacturer() { return this.newItemForm.get('manufacturer'); }
  get description() { return this.newItemForm.get('description'); }
  get price() { return this.newItemForm.get('price'); }
  get stockNumber() { return this.newItemForm.get('stockNumber'); }
  get thumbnail() { return this.newItemForm.get('thumbnail'); }
  get detailImage1() { return this.newItemForm.get('detailImage1'); }
  get detailImage2() { return this.newItemForm.get('detailImage2'); }
  get detailImage3() { return this.newItemForm.get('detailImage3'); }
  get detailImage4() { return this.newItemForm.get('detailImage4'); }
  get detailImage5() { return this.newItemForm.get('detailImage5'); }
  get remark() { return this.newItemForm.get('remark'); }

  private static urlPattern = '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?';

  @Output() closePop: EventEmitter<any> = new EventEmitter();

  newItemForm = this.formBuilder.group({
    catagory: new FormControl('', [Validators.required]),
    subCatagory: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required]),
    manufacturer: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.min(1)]),
    stockNumber: new FormControl('', [Validators.required, Validators.min(0)]),
    thumbnail: new FormControl('', [Validators.required, Validators.pattern(NewItemComponent.urlPattern)]),
    detailImage1: new FormControl('', [Validators.required, Validators.pattern(NewItemComponent.urlPattern)]),
    detailImage2: new FormControl('', [Validators.required, Validators.pattern(NewItemComponent.urlPattern)]),
    detailImage3: new FormControl('', [Validators.required, Validators.pattern(NewItemComponent.urlPattern)]),
    detailImage4: new FormControl('', [Validators.required, Validators.pattern(NewItemComponent.urlPattern)]),
    detailImage5: new FormControl('', [Validators.required, Validators.pattern(NewItemComponent.urlPattern)]),
    remark: new FormControl('', [Validators.required])
  });

  errorMessage: string;

  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: 'auto',
    minHeight: '0',
    maxHeight: 'auto',
    width: 'auto',
    minWidth: '0',
    translate: 'yes',
    enableToolbar: true,
    showToolbar: true,
    placeholder: 'Enter text here...',
    defaultParagraphSeparator: '',
    defaultFontName: '',
    defaultFontSize: '',
    fonts: [
      { class: 'arial', name: 'Arial' },
      { class: 'times-new-roman', name: 'Times New Roman' },
      { class: 'calibri', name: 'Calibri' },
      { class: 'comic-sans-ms', name: 'Comic Sans MS' }
    ],
    customClasses: [
      {
        name: 'quote',
        class: 'quote',
      },
      {
        name: 'redText',
        class: 'redText'
      },
      {
        name: 'titleText',
        class: 'titleText',
        tag: 'h1',
      },
    ],
    uploadUrl: 'v1/image',
    uploadWithCredentials: false,
    sanitize: true,
    toolbarPosition: 'top',
    toolbarHiddenButtons: [
      ['bold', 'italic'],
      ['fontSize']
    ]
  };

  catagories: object[] = new Array();
  subCatagories: object[] = new Array();

  ngOnInit(): void {
    this.apiService.getCatagories().subscribe(data => {
      data.forEach(catagory => {
        this.catagories.push({
          id: catagory.id,
          name: catagory.name
        });
      });
    });
  }

  onCatagoryChange(id: string) {
    if (id !== '') {
      this.apiService.getSubCatagories(id).subscribe(data => {
        data.forEach(subCatagory => {
          this.subCatagories.push({
            id: subCatagory.id,
            name: subCatagory.name
          });
        });
      });
    }
    this.subCatagory.setValue('');
  }


  onSubmit(): void {
    if (this.newItemForm.invalid) { return; }

    const item = {
      catagoryId: this.catagory.value,
      subcatagoryId: this.subCatagory.value,
      price: this.price.value,
      stockNumber: this.stockNumber.value,
      remark: this.remark.value,
      description: this.description.value,
      sellerId: this.cacheService.getItem<EmartUser>('emart-user').id,
      thumbnail: this.thumbnail.value,
      detailImage1: this.detailImage1.value,
      detailImage2: this.detailImage2.value,
      detailImage3: this.detailImage3.value,
      detailImage4: this.detailImage4.value,
      detailImage5: this.detailImage5.value,
      manufacturer: this.manufacturer.value,
      name: this.name.value
    };

    this.apiService.newItem(item).subscribe(newItem => {
      this.closePop.emit(newItem);
    }, error => {
      if (error) {
        this.errorMessage = error.message;
      }
    });

  }

}
