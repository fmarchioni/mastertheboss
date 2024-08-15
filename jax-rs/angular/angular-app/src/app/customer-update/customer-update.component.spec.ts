import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerUpdateComponent } from './customer-update.component';

describe('CustomerUpdateComponent', () => {
  let component: CustomerUpdateComponent;
  let fixture: ComponentFixture<CustomerUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
