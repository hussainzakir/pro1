import { TestBed } from '@angular/core/testing';

import { PriceIncreaseService } from './price-increase.service';

describe('PriceIncreaseService', () => {
  let service: PriceIncreaseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PriceIncreaseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
