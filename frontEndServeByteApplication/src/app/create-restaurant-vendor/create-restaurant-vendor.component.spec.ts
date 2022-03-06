import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRestaurantVendorComponent } from './create-restaurant-vendor.component';

describe('CreateRestaurantVendorComponent', () => {
  let component: CreateRestaurantVendorComponent;
  let fixture: ComponentFixture<CreateRestaurantVendorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateRestaurantVendorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateRestaurantVendorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
