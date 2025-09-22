/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { AAEContactEditComponent } from './aae-contact-edit.component';

describe('AAEContactEditComponent', () => {
  let component: AAEContactEditComponent;
  let fixture: ComponentFixture<AAEContactEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AAEContactEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AAEContactEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
