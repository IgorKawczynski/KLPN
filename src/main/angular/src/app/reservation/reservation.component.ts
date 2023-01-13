import { Component, OnInit } from '@angular/core';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { ReservationRequestDto } from './reservation-request';
import { ReservationService } from './reservation.service';
import {Router} from "@angular/router";
import { MessageService } from 'primeng/api';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {

  defaultFirstPitchClass = 'pitch first';
  defaultSecondPitchClass = 'pitch second';
  defaultThirdPitchClass = 'pitch third';
  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  reservationRequestDto: ReservationRequestDto = new ReservationRequestDto;

  constructor (
    private reservationService: ReservationService,
    private router: Router,
    private messageService: MessageService,
    private loginService: LoginService) {
   }

  ngOnInit(): void {
    this.reservationRequestDto.userId = this.loginService.getId();
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
    this.reservationRequestDto.pitch = pitchNumber;
  }

  btnConfirm(): void {
    this.reservationRequestDto.date.setHours(this.reservationRequestDto.date.getHours() + 1);
    this.createReservation();
  }

  btnCancel() {
    history.back();
  }

  public createReservation() {
    this.reservationService
    .createReservation(this.reservationRequestDto)
    .subscribe( (response: any) => {
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 8000, severity:'success', summary:'Zarezerwowano', detail:'Pomyślnie zarezerwowano boisko!'});
        setTimeout(() => {this.router.navigateByUrl('/reservation-list');}, 1500);
      }
    })
  }

}
