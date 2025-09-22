/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SiteEditComponent } from './site-edit.component';

describe('SiteEditComponent', () => {
  let component: SiteEditComponent;
  let fixture: ComponentFixture<SiteEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
