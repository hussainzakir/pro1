import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MassUploadHistoryComponent } from './mass-upload-history.component';

describe('MassUploadHistoryComponent', () => {
  let component: MassUploadHistoryComponent;
  let fixture: ComponentFixture<MassUploadHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MassUploadHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MassUploadHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
