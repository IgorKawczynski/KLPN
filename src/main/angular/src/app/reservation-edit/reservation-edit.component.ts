import { Component, OnInit } from '@angular/core';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { ReservationUpdateDto } from './reservation-update-dto';
import { ReservationEditService } from './reservation-edit.service';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';
import { ReservationResponseDTO } from '../reservation-list/reservation-response';

@Component({
  selector: 'app-reservation-edit',
  templateUrl: './reservation-edit.component.html',
  styleUrls: ['./reservation-edit.component.scss']
})
export class ReservationEditComponent implements OnInit {

  defaultFirstPitchClass = 'pitch first';
  defaultSecondPitchClass = 'pitch second';
  defaultThirdPitchClass = 'pitch third';
  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  reservationUpdateDto: ReservationUpdateDto = new ReservationUpdateDto;
  reservation: ReservationResponseDTO = new ReservationResponseDTO;

  constructor(
    private reservationEditService: ReservationEditService,
    private router: Router,
    private messageService: MessageService,
    private _Activatedroute: ActivatedRoute,
    private loginService: LoginService
    ) { }

  ngOnInit(): void {
    this.getData();
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
          this.messageService.add({life: 7000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 7000, severity:'success', summary:'Edytowano rezerwację', detail:'Pomyślnie zmieniono dane rezerwacji!'});
        setTimeout(() => {this.router.navigateByUrl('/reservation-list');}, 1500);
      }
    })
  }

  public getReservation(): void {
    let id: number = this._Activatedroute.snapshot.params['id'];
    this.reservationEditService.getReservationById(id).subscribe((response: any) => {
      this.reservation = response;
    });
  }

  public getData(): void {
    this.getReservation();
    this.reservationUpdateDto.userId = this.loginService.getId();
    this.reservationUpdateDto.id = this._Activatedroute.snapshot.params['id'];
  }

}
