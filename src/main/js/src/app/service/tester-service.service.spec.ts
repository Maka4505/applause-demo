import { TestBed } from '@angular/core/testing';

import { TesterServiceService } from './tester-service.service';

describe('TesterServiceService', () => {
  let service: TesterServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TesterServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
