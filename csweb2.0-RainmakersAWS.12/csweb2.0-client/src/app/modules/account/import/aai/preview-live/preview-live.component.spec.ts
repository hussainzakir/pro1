import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewLiveComponent } from './preview-live.component';

describe('PreviewLiveComponent', () => {
  let component: PreviewLiveComponent;
  let fixture: ComponentFixture<PreviewLiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreviewLiveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreviewLiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
