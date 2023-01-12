import { Component, OnInit } from '@angular/core';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { ReservationUpdateDto } from './reservation-update-dto';
import { ReservationEditService } from './reservation-edit.service';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reservation-edit',
  templateUrl: './reservation-edit.component.html',
  styleUrls: ['./reservation-edit.component.scss']
})
export class ReservationEditComponent implements OnInit {

  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  reservationUpdateDto: ReservationUpdateDto = new ReservationUpdateDto;

  constructor(
    private reservationEditService: ReservationEditService,
    private router: Router,
    private messageService: MessageService,
    private _Activatedroute: ActivatedRoute,
    ) { }

  ngOnInit(): void {
    this.reservationUpdateDto.userId = Number(localStorage.getItem("id"));
    this.reservationUpdateDto.id = this._Activatedroute.snapshot.params['id'];
  }

  setPitchNumber(pitchNumber: number): void {
    this.reservationUpdateDto.pitch = pitchNumber;
  }

  btnConfirm(): void {
    this.reservationUpdateDto.date.setHours(this.reservationUpdateDto.date.getHours() + 1);

    this.updateReservation();
  }

  btnCancel() {
    history.back();
  }

  public updateReservation() {
    this.reservationEditService
    .updateReservation(this.reservationUpdateDto)
    .subscribe( (response: any) => {
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 8000, severity:'success', summary:'Edytowano rezerwację', detail:'Pomyślnie zmieniono dane rezerwacji!'});
        setTimeout(location.reload.bind(location), 3000);
      }
    })
  }

}
