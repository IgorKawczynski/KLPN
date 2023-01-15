import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';
import { MatchForStudentResponseDTO } from '../student-matches/match-for-student';
import { MatchDateEditDTO } from './match-date-edit';
import { MatchDateEditService } from './match-date-edit.service';

@Component({
  selector: 'app-match-date-edit',
  templateUrl: './match-date-edit.component.html',
  styleUrls: ['./match-date-edit.component.scss', '../reservation-edit/reservation-edit.component.scss']
})
export class MatchDateEditComponent implements OnInit {

  defaultFirstPitchClass = 'pitch first';
  defaultSecondPitchClass = 'pitch second';
  defaultThirdPitchClass = 'pitch third';
  public reservationIdOfActuallyEditedMatch = this.activatedRoute.snapshot.params['id'];
  public matchDateEditDTO: MatchDateEditDTO = new MatchDateEditDTO();
  public matchToEdit: MatchForStudentResponseDTO = new MatchForStudentResponseDTO();

  constructor(
    private loginService: LoginService,
    private activatedRoute: ActivatedRoute,
    private matchEditService: MatchDateEditService,
    ) { }

  ngOnInit(): void {
    this.getMatchToEdit();
    this.matchDateEditDTO.userId = this.loginService.getId();
    this.matchDateEditDTO.reservationId = this.reservationIdOfActuallyEditedMatch;
  }

  setPitchNumber(pitchNumber: number): void {
    if(pitchNumber == 1){
      this.defaultFirstPitchClass = 'clicked'
      this.defaultSecondPitchClass = 'hidden'
      this.defaultThirdPitchClass = 'hidden'
    }
    else if(pitchNumber == 2) {
      this.defaultSecondPitchClass = 'clicked'
      this.defaultFirstPitchClass = 'hidden'
      this.defaultThirdPitchClass = 'hidden'
    }
    else {
      this.defaultThirdPitchClass = 'clicked'
      this.defaultFirstPitchClass = 'hidden'
      this.defaultSecondPitchClass = 'hidden'
    }
    this.matchDateEditDTO.pitch = pitchNumber;
  }

  public btnCancel() {
    history.back();
  }

  public btnConfirm() {
    console.log('zmiana ;D');
    console.log(this.matchDateEditDTO);
  }

  public getMatchToEdit() {
    this.matchEditService
      .getMatchToEdit(this.reservationIdOfActuallyEditedMatch).subscribe((response) => {
        this.matchToEdit = response;
      })
  }

}
