/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { FutureServiceRequestDeleteComponent } from './fsr-delete.component';

describe('FutureServiceRequestDeleteComponent', () => {
  let component: FutureServiceRequestDeleteComponent;
  let fixture: ComponentFixture<FutureServiceRequestDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FutureServiceRequestDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FutureServiceRequestDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
