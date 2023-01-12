import { TestBed } from '@angular/core/testing';

import { ReservationEditService } from './reservation-edit.service';

describe('ReservationEditService', () => {
  let service: ReservationEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReservationEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
