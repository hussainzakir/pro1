import { TestBed } from '@angular/core/testing';
import { ObligationsSearchServiceService } from './obligations-search-service.service';

describe('ObligationsSearchServiceService', () => {
  let service: ObligationsSearchServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ObligationsSearchServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
