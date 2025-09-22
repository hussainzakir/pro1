import { TestBed } from '@angular/core/testing';

import { MuTemplateServiceService } from './mu-template-service.service';

describe('MuTemplateServiceService', () => {
  let service: MuTemplateServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MuTemplateServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
