import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerSignupComponent } from './seller-signup.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('SellerSignupComponent', () => {
  let component: SellerSignupComponent;
  let fixture: ComponentFixture<SellerSignupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [ SellerSignupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellerSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
