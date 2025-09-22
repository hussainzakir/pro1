import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AaeTemplatesComponent } from './aae-templates.component';

describe('AaeTemplatesComponent', () => {
  let component: AaeTemplatesComponent;
  let fixture: ComponentFixture<AaeTemplatesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AaeTemplatesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AaeTemplatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
