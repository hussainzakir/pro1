/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { AccountImports3Component } from './account-imports3.component';

describe('AccountImports3Component', () => {
  let component: AccountImports3Component;
  let fixture: ComponentFixture<AccountImports3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountImports3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountImports3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
