/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { MaintainServiceRecordingsComponent } from './maintain-service-recordings.component';

describe('MaintainServiceRecordingsComponent', () => {
  let component: MaintainServiceRecordingsComponent;
  let fixture: ComponentFixture<MaintainServiceRecordingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaintainServiceRecordingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaintainServiceRecordingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
