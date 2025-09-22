import { TestBed } from '@angular/core/testing';

import { AuditserviceService } from './audit-service.service';

describe('AuditserviceService', () => {
  let service: AuditserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuditserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
