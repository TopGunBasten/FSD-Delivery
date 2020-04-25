import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShoppingHomeComponent } from './shopping-home.component';
import { RouterTestingModule } from '@angular/router/testing';
import { ShoppingHeaderComponent } from '../shopping-header/shopping-header.component';

describe('ShoppingHomeComponent', () => {
  let component: ShoppingHomeComponent;
  let fixture: ComponentFixture<ShoppingHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule
      ],
      declarations: [ ShoppingHomeComponent, ShoppingHeaderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShoppingHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
