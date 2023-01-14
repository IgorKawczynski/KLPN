import { Component, OnInit } from '@angular/core';
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
    private loginService: LoginService,
    private studentMatchesService: StudentMatchesService,
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

}
