import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicenumberfieldComponent } from './servicenumberfield.component';

describe('ServicenumberfieldComponent', () => {
  let component: ServicenumberfieldComponent;
  let fixture: ComponentFixture<ServicenumberfieldComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServicenumberfieldComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServicenumberfieldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
