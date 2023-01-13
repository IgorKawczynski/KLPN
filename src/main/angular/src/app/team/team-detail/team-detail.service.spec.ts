import { TestBed } from '@angular/core/testing';

import { TeamDetailService } from './team-detail.service';

describe('TeamDetailService', () => {
  let service: TeamDetailService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeamDetailService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
