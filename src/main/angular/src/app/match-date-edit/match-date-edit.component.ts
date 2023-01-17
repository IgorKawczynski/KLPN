import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from '../basic/error-list/error-list';
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
  public errorsListDTO: ErrorsListDTO = new ErrorsListDTO();

  constructor(
    private loginService: LoginService,
    private activatedRoute: ActivatedRoute,
    private matchEditService: MatchDateEditService,
    private messageService: MessageService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.getMatchToEdit();
    this.matchDateEditDTO.captainId = this.loginService.getId();
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
    this.matchDateEditDTO.newMatchDate.setHours(this.matchDateEditDTO.newMatchDate.getHours() + 1);
    this.sendMatchDateEditRequest();
  }

  public getMatchToEdit() {
    this.matchEditService
      .getMatchToEdit(this.reservationIdOfActuallyEditedMatch).subscribe((response: any) => {
        this.matchToEdit = response;
      })
  }

  public sendMatchDateEditRequest() {
    this.matchEditService
    .sendMatchToEditRequest(this.matchDateEditDTO).subscribe((response: any) => {
      this.errorsListDTO = response;
      if(!this.errorsListDTO.listOfErrorsEmpty) {
        this.errorsListDTO.errors.forEach((error) => {
          this.messageService.add({life: 7000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 7000, severity:'success', summary:'Edycja daty spotkania', detail:'Twoja prośba o zmianę terminu spotkania została przesłana do Administratora.'});
        setTimeout(() => {this.router.navigateByUrl('/student-matches');}, 1500);
      }
    })
  }

}
