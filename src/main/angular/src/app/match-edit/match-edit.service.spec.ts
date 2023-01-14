import { TestBed } from '@angular/core/testing';

import { MatchEditService } from './match-edit.service';

describe('MatchEditService', () => {
  let service: MatchEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
