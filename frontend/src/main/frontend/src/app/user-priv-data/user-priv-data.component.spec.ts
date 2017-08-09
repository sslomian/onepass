import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPrivDataComponent } from './user-priv-data.component';

describe('UserPrivDataComponent', () => {
  let component: UserPrivDataComponent;
  let fixture: ComponentFixture<UserPrivDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserPrivDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPrivDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
