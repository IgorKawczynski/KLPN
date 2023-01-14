import { TestBed } from '@angular/core/testing';

import { TeamEditService } from './team-edit.service';

describe('TeamEditService', () => {
  let service: TeamEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeamEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
