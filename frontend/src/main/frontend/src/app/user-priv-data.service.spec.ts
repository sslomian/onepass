import { TestBed, inject } from '@angular/core/testing';

import { UserPrivDataService } from './user-priv-data.service';

describe('UserPrivDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserPrivDataService]
    });
  });

  it('should be created', inject([UserPrivDataService], (service: UserPrivDataService) => {
    expect(service).toBeTruthy();
  }));
});
