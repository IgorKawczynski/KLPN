import { TestBed } from '@angular/core/testing';

import { TransferListService } from './transfer-list.service';

describe('TransferListService', () => {
  let service: TransferListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransferListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
