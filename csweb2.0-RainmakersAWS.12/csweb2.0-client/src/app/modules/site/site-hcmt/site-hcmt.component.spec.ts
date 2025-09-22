/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SiteHcmtComponent } from './site-hcmt.component';

describe('SiteHcmtComponent', () => {
  let component: SiteHcmtComponent;
  let fixture: ComponentFixture<SiteHcmtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteHcmtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteHcmtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
