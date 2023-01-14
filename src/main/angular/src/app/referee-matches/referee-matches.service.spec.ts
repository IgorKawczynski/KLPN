import { TestBed } from '@angular/core/testing';

import { RefereeMatchesService } from './referee-matches.service';

describe('RefereeMatchesService', () => {
  let service: RefereeMatchesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RefereeMatchesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
