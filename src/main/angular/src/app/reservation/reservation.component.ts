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
  }

  setPitchNumber(pitchNumber: number): void {
    this.reservationRequestDto.pitch = pitchNumber;
  }

  btnConfirm(): void {
    console.log(this.reservationRequestDto);
    this.reservationRequestDto.userId = 1001;
    this.createReservation();
  }

  btnRoute(url: string) {
    this.router.navigateByUrl(url);
  }

  public createReservation() {
    this.reservationService
    .createReservation(this.reservationRequestDto)
    .subscribe( (response: any) => {
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Reservation', detail:error})
        });
      }
      else{
        this.messageService.add({life: 8000, severity:'success', summary:'Reservation', detail:'Successfully created reservation!'});
      }
    })
  }

}
