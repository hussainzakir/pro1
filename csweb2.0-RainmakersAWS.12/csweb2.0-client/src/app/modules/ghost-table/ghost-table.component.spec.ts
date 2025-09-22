/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { GhostTableComponent } from './ghost-table.component';

describe('GhostTableComponent', () => {
  let component: GhostTableComponent;
  let fixture: ComponentFixture<GhostTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GhostTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GhostTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
