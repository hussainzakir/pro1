import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitMassUploadComponent } from './submit-mass-upload.component';

describe('SubmitMassUploadComponent', () => {
  let component: SubmitMassUploadComponent;
  let fixture: ComponentFixture<SubmitMassUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubmitMassUploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmitMassUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
