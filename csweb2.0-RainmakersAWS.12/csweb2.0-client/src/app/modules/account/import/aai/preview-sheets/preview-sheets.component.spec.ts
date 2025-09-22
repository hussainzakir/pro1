import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewSheetsComponent } from './preview-sheets.component';

describe('PreviewSheetsComponent', () => {
  let component: PreviewSheetsComponent;
  let fixture: ComponentFixture<PreviewSheetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviewSheetsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewSheetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
