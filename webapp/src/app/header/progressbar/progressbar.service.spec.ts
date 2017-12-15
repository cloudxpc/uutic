import { TestBed, inject } from '@angular/core/testing';

import { ProgressbarService } from './progressbar.service';

describe('ProgressbarService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProgressbarService]
    });
  });

  it('should be created', inject([ProgressbarService], (service: ProgressbarService) => {
    expect(service).toBeTruthy();
  }));
});
