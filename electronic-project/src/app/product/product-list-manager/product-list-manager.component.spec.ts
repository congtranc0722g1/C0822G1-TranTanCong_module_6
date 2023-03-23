import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductListManagerComponent } from './product-list-manager.component';

describe('ProductListManagerComponent', () => {
  let component: ProductListManagerComponent;
  let fixture: ComponentFixture<ProductListManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductListManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
