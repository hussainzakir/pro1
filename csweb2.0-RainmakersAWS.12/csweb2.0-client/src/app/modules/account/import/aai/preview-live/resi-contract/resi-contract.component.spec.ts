import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResiContractComponent } from './resi-contract.component';

describe('ResiContractComponent', () => {
  let component: ResiContractComponent;
  let fixture: ComponentFixture<ResiContractComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResiContractComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResiContractComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
