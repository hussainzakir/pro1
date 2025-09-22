import { TestBed } from '@angular/core/testing';

import { RowLimitService } from './row-limit.service';

describe('RowLimitService', () => {
  let service: RowLimitService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RowLimitService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
