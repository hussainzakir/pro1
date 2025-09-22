import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewSheetsComponent3 } from './preview-sheets.component';

describe('PreviewSheetsComponent', () => {
  let component: PreviewSheetsComponent3;
  let fixture: ComponentFixture<PreviewSheetsComponent3>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviewSheetsComponent3 ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewSheetsComponent3);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
