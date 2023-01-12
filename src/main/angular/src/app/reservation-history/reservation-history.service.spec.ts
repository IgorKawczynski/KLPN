import { TestBed } from '@angular/core/testing';

import { ReservationHistoryService } from './reservation-history.service';

describe('ReservationHistoryService', () => {
  let service: ReservationHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservationHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
