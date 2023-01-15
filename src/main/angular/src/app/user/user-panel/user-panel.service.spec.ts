import { TestBed } from '@angular/core/testing';

import { UserPanelService } from './user-panel.service';

describe('UserPanelService', () => {
  let service: UserPanelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserPanelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
