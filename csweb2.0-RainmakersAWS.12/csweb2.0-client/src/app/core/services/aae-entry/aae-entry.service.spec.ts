/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { AaeEntryService } from './aae-entry.service';

describe('AaeEntryService', () => {
  let service: AaeEntryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AaeEntryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
