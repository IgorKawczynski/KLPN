import { TestBed } from '@angular/core/testing';

import { StudentMatchesService } from './student-matches.service';

describe('StudentMatchesService', () => {
  let service: StudentMatchesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StudentMatchesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
