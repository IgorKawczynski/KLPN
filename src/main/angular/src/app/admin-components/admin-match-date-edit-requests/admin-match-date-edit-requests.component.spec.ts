import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminMatchDateEditRequestsComponent } from './admin-match-date-edit-requests.component';

describe('AdminMatchDateEditRequestsComponent', () => {
  let component: AdminMatchDateEditRequestsComponent;
  let fixture: ComponentFixture<AdminMatchDateEditRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminMatchDateEditRequestsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminMatchDateEditRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
