import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeMatchesComponent } from './referee-matches.component';

describe('RefereeMatchesComponent', () => {
  let component: RefereeMatchesComponent;
  let fixture: ComponentFixture<RefereeMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RefereeMatchesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RefereeMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
