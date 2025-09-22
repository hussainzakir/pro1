/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ServiceRecordingService } from './service-recording.service';

describe('Service: ServiceRecording', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServiceRecordingService]
    });
  });

  it('should ...', inject([ServiceRecordingService], (service: ServiceRecordingService) => {
    expect(service).toBeTruthy();
  }));
});
