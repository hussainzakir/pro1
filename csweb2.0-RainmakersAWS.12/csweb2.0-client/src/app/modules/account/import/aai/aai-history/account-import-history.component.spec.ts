import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountImportHistoryComponent } from './account-import-history.component';

describe('AccountImportHistoryComponent', () => {
  let component: AccountImportHistoryComponent;
  let fixture: ComponentFixture<AccountImportHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountImportHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountImportHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
