/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SiteContactEditComponent } from './site-contact-edit.component';

describe('SiteContactEditComponent', () => {
  let component: SiteContactEditComponent;
  let fixture: ComponentFixture<SiteContactEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteContactEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteContactEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
