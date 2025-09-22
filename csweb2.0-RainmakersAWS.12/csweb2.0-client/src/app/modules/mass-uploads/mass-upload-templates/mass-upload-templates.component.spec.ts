import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MassUploadTemplatesComponent } from './mass-upload-templates.component';

describe('MassUploadTemplatesComponent', () => {
  let component: MassUploadTemplatesComponent;
  let fixture: ComponentFixture<MassUploadTemplatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MassUploadTemplatesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MassUploadTemplatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
