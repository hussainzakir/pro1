import { TestBed } from '@angular/core/testing';

import { CsSearchServiceService } from './cs-search-service.service';

describe('CsSearchServiceService', () => {
  let service: CsSearchServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CsSearchServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
