import { Component, OnInit } from '@angular/core';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { ReservationRequestDto } from './reservation-request';
import { ReservationService } from './reservation.service';
import {Router} from "@angular/router";
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent implements OnInit {

  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  reservationRequestDto: ReservationRequestDto = new ReservationRequestDto;

  constructor (
    private reservationService: ReservationService,
    private router: Router,
    private messageService: MessageService) {
   }

  ngOnInit(): void {
    this.reservationRequestDto.userId = Number(localStorage.getItem("id"));
  }

  setPitchNumber(pitchNumber: number): void {
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
        setTimeout(location.reload.bind(location), 3000);
      }
    })
  }

}
