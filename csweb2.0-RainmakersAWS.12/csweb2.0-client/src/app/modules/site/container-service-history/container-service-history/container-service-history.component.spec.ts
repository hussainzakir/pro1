import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContainerServiceHistoryComponent } from './container-service-history.component';

describe('ContainerServiceHistoryComponent', () => {
  let component: ContainerServiceHistoryComponent;
  let fixture: ComponentFixture<ContainerServiceHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContainerServiceHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContainerServiceHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
