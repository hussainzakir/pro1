import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewmodalComponent } from './reviewmodal.component';

describe('ReviewmodalComponent', () => {
  let component: ReviewmodalComponent;
  let fixture: ComponentFixture<ReviewmodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewmodalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
