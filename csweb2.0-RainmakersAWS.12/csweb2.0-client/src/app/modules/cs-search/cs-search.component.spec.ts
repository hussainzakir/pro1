import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CsSearchComponent } from './cs-search.component';

describe('CsSearchComponent', () => {
  let component: CsSearchComponent;
  let fixture: ComponentFixture<CsSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CsSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
