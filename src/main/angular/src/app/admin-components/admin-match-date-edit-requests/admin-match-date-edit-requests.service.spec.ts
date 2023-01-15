import { TestBed } from '@angular/core/testing';

import { AdminMatchDateEditRequestsService } from './admin-match-date-edit-requests.service';

describe('AdminMatchDateEditRequestsService', () => {
  let service: AdminMatchDateEditRequestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminMatchDateEditRequestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
