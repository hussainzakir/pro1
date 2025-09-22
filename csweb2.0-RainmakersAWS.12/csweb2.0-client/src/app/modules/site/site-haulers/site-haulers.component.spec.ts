/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SiteHaulersComponent } from './site-haulers.component';

describe('SiteHaulersComponent', () => {
  let component: SiteHaulersComponent;
  let fixture: ComponentFixture<SiteHaulersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SiteHaulersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SiteHaulersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
