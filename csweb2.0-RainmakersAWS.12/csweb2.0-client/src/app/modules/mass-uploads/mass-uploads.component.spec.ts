import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MassUploadsComponent } from './mass-uploads.component';

describe('MassUploadsComponent', () => {
  let component: MassUploadsComponent;
  let fixture: ComponentFixture<MassUploadsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MassUploadsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MassUploadsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
