import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { MatchForStudentResponseDTO } from './match-for-student';
import { StudentMatchesService } from './student-matches.service';

@Component({
  selector: 'app-student-matches',
  templateUrl: './student-matches.component.html',
  styleUrls: ['./student-matches.component.scss']
})
export class StudentMatchesComponent implements OnInit {

  public studentMatches: MatchForStudentResponseDTO[] = [];

  constructor(
    public loginService: LoginService,
    private studentMatchesService: StudentMatchesService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.getMatches();
  }

  public getMatches(): void {
    let studentId = this.loginService.getId();
    this.studentMatchesService.getMatchesForStudent(studentId).subscribe((response: any) => {
      this.studentMatches = response;
    })
  }

  public btnEdit(reservationId: number): void {
    this.router.navigateByUrl(`/student-matches/edit-date/${reservationId}`);
  }

}
