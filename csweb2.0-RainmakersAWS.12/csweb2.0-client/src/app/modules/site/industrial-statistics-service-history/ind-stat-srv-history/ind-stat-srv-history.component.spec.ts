import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndStatSrvHistoryComponent } from './ind-stat-srv-history.component';

describe('IndStatSrvHistoryComponent', () => {
  let component: IndStatSrvHistoryComponent;
  let fixture: ComponentFixture<IndStatSrvHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndStatSrvHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndStatSrvHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
