import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentMatchesComponent } from './student-matches.component';

describe('StudentMatchesComponent', () => {
  let component: StudentMatchesComponent;
  let fixture: ComponentFixture<StudentMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentMatchesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
