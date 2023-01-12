import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAcceptTeamComponent } from './admin-accept-team.component';

describe('AdminAcceptTeamComponent', () => {
  let component: AdminAcceptTeamComponent;
  let fixture: ComponentFixture<AdminAcceptTeamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAcceptTeamComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAcceptTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
