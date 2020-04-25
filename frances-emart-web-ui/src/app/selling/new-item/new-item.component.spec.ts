import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewItemComponent } from './new-item.component';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { HttpClientModule } from '@angular/common/http';

describe('NewItemComponent', () => {
  let component: NewItemComponent;
  let fixture: ComponentFixture<NewItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        AngularEditorModule,
        HttpClientModule],
      declarations: [ NewItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
