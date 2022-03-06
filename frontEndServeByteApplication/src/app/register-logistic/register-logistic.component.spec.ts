import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterLogisticComponent } from './register-logistic.component';

describe('RegisterLogisticComponent', () => {
  let component: RegisterLogisticComponent;
  let fixture: ComponentFixture<RegisterLogisticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterLogisticComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterLogisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
