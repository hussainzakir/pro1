import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthenticatedlayoutComponent } from './authenticatedlayout.component';

describe('AuthenticatedlayoutComponent', () => {
  let component: AuthenticatedlayoutComponent;
  let fixture: ComponentFixture<AuthenticatedlayoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthenticatedlayoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthenticatedlayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
