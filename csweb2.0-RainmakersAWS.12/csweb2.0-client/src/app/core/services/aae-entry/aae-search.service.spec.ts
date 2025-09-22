import { TestBed } from '@angular/core/testing';

import { AaeSearchService } from './aae-search.service';

describe('AaeSearchService', () => {
  let service: AaeSearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AaeSearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
