import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchDateEditComponent } from './match-date-edit.component';

describe('MatchDateEditComponent', () => {
  let component: MatchDateEditComponent;
  let fixture: ComponentFixture<MatchDateEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatchDateEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MatchDateEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
