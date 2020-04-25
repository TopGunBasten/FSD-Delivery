import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellingReportComponent } from './selling-report.component';

describe('SellingReportComponent', () => {
  let component: SellingReportComponent;
  let fixture: ComponentFixture<SellingReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellingReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellingReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
