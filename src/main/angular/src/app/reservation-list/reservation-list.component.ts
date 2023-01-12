import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { ReservationResponseDTO } from './reservation-response';
import { ReservationListService } from './reservation-list.service';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.scss']
})
export class ReservationListComponent implements OnInit {

  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  public reservations: ReservationResponseDTO[] = [];

  public rowId!: number;

  constructor(
    private reservationListService: ReservationListService,
    private messageService: MessageService,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.getReservations();
  }

  public getReservations(): void {
    let userId: number = this.loginService.getId();
    
    this.reservationListService.getReservationsByUserId(userId).subscribe((response: any) => {
      this.reservations = response;
    });
  }

  public btnDelete(row: number){
    // this.deleteReservationById();
    // window.location.reload();
    console.log(row);
  }

  // public deleteReservationById(): void {
  //   this.reservationListService.deleteReservationById(this.rowId).subscribe((response: any) =>{

  //   });
  // }
}
