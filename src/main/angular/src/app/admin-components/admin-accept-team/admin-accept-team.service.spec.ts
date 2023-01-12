import { TestBed } from '@angular/core/testing';

import { AdminAcceptTeamService } from './admin-accept-team.service';

describe('AdminAcceptTeamService', () => {
  let service: AdminAcceptTeamService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminAcceptTeamService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
