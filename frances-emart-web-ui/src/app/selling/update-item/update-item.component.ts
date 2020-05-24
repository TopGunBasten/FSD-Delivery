import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { EmartCacheService } from 'src/app/emart-common/auth/emart-cache.service';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ApiClientService } from 'src/app/api-client.service';

@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.scss']
})
export class UpdateItemComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private apiService: ApiClientService,
              private cacheService: EmartCacheService) { }

  get catagory() { return this.updateItemForm.get('catagory'); }
  get subCatagory() { return this.updateItemForm.get('subCatagory'); }
  get manufacturer() { return this.updateItemForm.get('manufacturer'); }
  get name() { return this.updateItemForm.get('name'); }
  get description() { return this.updateItemForm.get('description'); }
  get price() { return this.updateItemForm.get('price'); }
  get thumbnail() { return this.updateItemForm.get('thumbnail'); }
  get detailImage1() { return this.updateItemForm.get('detailImage1'); }
  get detailImage2() { return this.updateItemForm.get('detailImage2'); }
  get detailImage3() { return this.updateItemForm.get('detailImage3'); }
  get detailImage4() { return this.updateItemForm.get('detailImage4'); }
  get detailImage5() { return this.updateItemForm.get('detailImage5'); }
  get remark() { return this.updateItemForm.get('remark'); }

  private static urlPattern = '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?';

  @Input() item: any;

  @Output() closeUpdatePop: EventEmitter<any> = new EventEmitter();

  errorMessage: string;

  updateItemForm = null;

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
        {class: 'arial', name: 'Arial'},
        {class: 'times-new-roman', name: 'Times New Roman'},
        {class: 'calibri', name: 'Calibri'},
        {class: 'comic-sans-ms', name: 'Comic Sans MS'}
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
   this.updateItemForm = this.formBuilder.group({
      catagory: new FormControl(this.item.catagoryId, [Validators.required]),
      subCatagory: new FormControl(this.item.subCatagoryId, [Validators.required]),
      name: new FormControl(this.item.name, [Validators.required]),
      manufacturer: new FormControl(this.item.manufacturer, [Validators.required]),
      description: new FormControl(this.item.description, [Validators.required]),
      price: new FormControl(this.item.price, [Validators.required, Validators.min(1)]),
      thumbnail: new FormControl(this.item.thumbnail, [Validators.required, Validators.pattern(UpdateItemComponent.urlPattern)]),
      detailImage1: new FormControl(this.item.detailImage1, [Validators.required, Validators.pattern(UpdateItemComponent.urlPattern)]),
      detailImage2: new FormControl(this.item.detailImage2, [Validators.required, Validators.pattern(UpdateItemComponent.urlPattern)]),
      detailImage3: new FormControl(this.item.detailImage3, [Validators.required, Validators.pattern(UpdateItemComponent.urlPattern)]),
      detailImage4: new FormControl(this.item.detailImage4, [Validators.required, Validators.pattern(UpdateItemComponent.urlPattern)]),
      detailImage5: new FormControl(this.item.detailImage5, [Validators.required, Validators.pattern(UpdateItemComponent.urlPattern)]),
      remark: new FormControl(this.item.remark, [Validators.required])
    });

   this.apiService.getCatagories().subscribe(data => {
      data.forEach(catagory => {
        this.catagories.push({
          id: catagory.id,
          name: catagory.name
        });
      });
    });
   this.apiService.getSubCatagories(this.item.catagoryId).subscribe(data => {
      data.forEach(subCatagory => {
        this.subCatagories.push({
          id: subCatagory.id,
          name: subCatagory.name
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
    if (this.updateItemForm.invalid) { return; }

    const item = {
      id: this.item.id,
      catagoryId: this.catagory.value,
      subcatagoryId: this.subCatagory.value,
      price: this.price.value,
      remark: this.remark.value,
      description: this.description.value,
      thumbnail: this.thumbnail.value,
      detailImage1: this.detailImage1.value,
      detailImage2: this.detailImage2.value,
      detailImage3: this.detailImage3.value,
      detailImage4: this.detailImage4.value,
      detailImage5: this.detailImage5.value,
      manufacturer: this.manufacturer.value,
      name: this.name.value
    };

    this.apiService.updateItem(item).subscribe(updateItem => {
      this.closeUpdatePop.emit(updateItem);
    }, error => {
      if (error) {
        this.errorMessage = error.message;
      }
    });

  }
}
