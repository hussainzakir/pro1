/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { AAEAccountEditComponent } from './aae-account-edit.component';

describe('AccountEditComponent', () => {
  let component: AAEAccountEditComponent;
  let fixture: ComponentFixture<AAEAccountEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AAEAccountEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AAEAccountEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
