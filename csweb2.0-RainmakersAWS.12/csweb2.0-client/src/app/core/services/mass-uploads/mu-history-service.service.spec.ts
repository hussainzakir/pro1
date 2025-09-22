import { TestBed } from '@angular/core/testing';

import { MuHistoryServiceService } from './mu-history-service.service';

describe('MuHistoryServiceService', () => {
  let service: MuHistoryServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MuHistoryServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
