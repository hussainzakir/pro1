import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObligationsSearchComponent } from './obligations-search.component';

describe('ObligationsSearchComponent', () => {
  let component: ObligationsSearchComponent;
  let fixture: ComponentFixture<ObligationsSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObligationsSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObligationsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
