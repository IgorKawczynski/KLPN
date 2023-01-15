import { TestBed } from '@angular/core/testing';

import { MatchDateEditService } from './match-date-edit.service';

describe('MatchDateEditService', () => {
  let service: MatchDateEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchDateEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
