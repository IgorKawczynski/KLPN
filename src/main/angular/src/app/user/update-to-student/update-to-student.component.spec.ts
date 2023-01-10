import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateToStudentComponent } from './update-to-student.component';

describe('UpdateToStudentComponent', () => {
  let component: UpdateToStudentComponent;
  let fixture: ComponentFixture<UpdateToStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateToStudentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateToStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
