import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellingHomeComponent } from './selling-home.component';
import { RouterTestingModule } from '@angular/router/testing';
import { SellingHeaderComponent } from '../selling-header/selling-header.component';

describe('SellingHomeComponent', () => {
  let component: SellingHomeComponent;
  let fixture: ComponentFixture<SellingHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [ SellingHomeComponent, SellingHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellingHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
