import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationEditComponent } from './reservation-edit.component';

describe('ReservationEditComponent', () => {
  let component: ReservationEditComponent;
  let fixture: ComponentFixture<ReservationEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReservationEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
