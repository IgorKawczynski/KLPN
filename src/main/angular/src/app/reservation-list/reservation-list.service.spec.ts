import { TestBed } from '@angular/core/testing';

import { ReservationListService } from './reservation-list.service';

describe('ReservationListService', () => {
  let service: ReservationListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservationListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
