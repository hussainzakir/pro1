import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnonymouslayoutComponent } from './anonymouslayout.component';

describe('AnonymouslayoutComponent', () => {
  let component: AnonymouslayoutComponent;
  let fixture: ComponentFixture<AnonymouslayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnonymouslayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnonymouslayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
