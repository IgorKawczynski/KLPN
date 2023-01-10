import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAcceptPlayerComponent } from './admin-accept-player.component';

describe('AdminAcceptPlayerComponent', () => {
  let component: AdminAcceptPlayerComponent;
  let fixture: ComponentFixture<AdminAcceptPlayerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAcceptPlayerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAcceptPlayerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
