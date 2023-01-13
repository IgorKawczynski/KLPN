import { TestBed } from '@angular/core/testing';

import { TeamManageService } from './team-manage.service';

describe('TeamManageService', () => {
  let service: TeamManageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeamManageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
