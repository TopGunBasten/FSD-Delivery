import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellingHeaderComponent } from './selling-header.component';
import { RouterTestingModule } from '@angular/router/testing';


describe('SellingHeaderComponent', () => {
  let component: SellingHeaderComponent;
  let fixture: ComponentFixture<SellingHeaderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [ SellingHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellingHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
