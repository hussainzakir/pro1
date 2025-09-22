/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SiteRatesComponent } from './site-rates.component';

describe('SiteRatesComponent', () => {
  let component: SiteRatesComponent;
  let fixture: ComponentFixture<SiteRatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteRatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteRatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
