import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceIncreaseUploadComponent } from './price-increase-upload.component';

describe('PriceIncreaseUploadComponent', () => {
  let component: PriceIncreaseUploadComponent;
  let fixture: ComponentFixture<PriceIncreaseUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceIncreaseUploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceIncreaseUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
