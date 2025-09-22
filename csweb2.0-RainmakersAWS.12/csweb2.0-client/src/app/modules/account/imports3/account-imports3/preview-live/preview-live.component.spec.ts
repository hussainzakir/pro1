import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewLiveComponent3 } from './preview-live.component';

describe('PreviewLiveComponent3', () => {
  let component: PreviewLiveComponent3;
  let fixture: ComponentFixture<PreviewLiveComponent3>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviewLiveComponent3 ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewLiveComponent3);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
