/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { AAESiteEditComponent } from './aae-site-edit.component';

describe('SiteEditComponent', () => {
  let component: AAESiteEditComponent;
  let fixture: ComponentFixture<AAESiteEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AAESiteEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AAESiteEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
