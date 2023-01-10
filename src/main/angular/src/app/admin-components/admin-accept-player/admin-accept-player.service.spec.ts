import { TestBed } from '@angular/core/testing';

import { AdminAcceptPlayerService } from './admin-accept-player.service';

describe('AdminAcceptPlayerService', () => {
  let service: AdminAcceptPlayerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminAcceptPlayerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
