import { TestBed } from '@angular/core/testing';

import { WorkationService } from './workation';

describe('Workation', () => {
  let service: WorkationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
