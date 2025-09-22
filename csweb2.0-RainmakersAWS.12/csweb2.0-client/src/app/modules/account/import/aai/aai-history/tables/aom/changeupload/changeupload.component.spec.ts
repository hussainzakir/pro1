import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeuploadComponent } from './changeupload.component';

describe('ChangeuploadComponent', () => {
  let component: ChangeuploadComponent;
  let fixture: ComponentFixture<ChangeuploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeuploadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeuploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
