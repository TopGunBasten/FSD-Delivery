import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyerSignupComponent } from './buyer-signup.component';
import { RouterTestingModule } from '@angular/router/testing';


describe('BuyerSignupComponent', () => {
  let component: BuyerSignupComponent;
  let fixture: ComponentFixture<BuyerSignupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [ BuyerSignupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyerSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
