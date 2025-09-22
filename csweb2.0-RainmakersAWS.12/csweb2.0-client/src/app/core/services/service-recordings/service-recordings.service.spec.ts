/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ServiceRecordingsService } from './service-recordings.service';

describe('Service: ServiceRecordings', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServiceRecordingsService]
    });
  });

  it('should ...', inject([ServiceRecordingsService], (service: ServiceRecordingsService) => {
    expect(service).toBeTruthy();
  }));
});
