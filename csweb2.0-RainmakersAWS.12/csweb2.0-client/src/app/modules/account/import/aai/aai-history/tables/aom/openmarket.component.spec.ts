import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenmarketComponent } from './openmarket.component';

describe('OpenmarketComponent', () => {
  let component: OpenmarketComponent;
  let fixture: ComponentFixture<OpenmarketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OpenmarketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenmarketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
