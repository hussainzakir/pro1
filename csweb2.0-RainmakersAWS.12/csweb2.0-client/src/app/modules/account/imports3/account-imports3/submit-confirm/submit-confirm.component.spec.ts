import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitConfirmComponent3 } from './submit-confirm.component';

describe('SubmitConfirmComponent', () => {
  let component: SubmitConfirmComponent3;
  let fixture: ComponentFixture<SubmitConfirmComponent3>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubmitConfirmComponent3 ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmitConfirmComponent3);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
