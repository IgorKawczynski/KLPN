import { Component, OnInit } from '@angular/core';
import { ReservationResponseDTO } from '../reservation-list/reservation-response';
import { ReservationHistoryService } from './reservation-history.service';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-reservation-history',
  templateUrl: './reservation-history.component.html',
  styleUrls: ['./reservation-history.component.scss']
})
export class ReservationHistoryComponent implements OnInit {

  public pastReservations: ReservationResponseDTO[] = [];

  constructor(
    private reservationHistoryService: ReservationHistoryService,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.getPastReservations();
  }

  public getPastReservations(): void {
    let userId: number = this.loginService.getId();

    this.reservationHistoryService.getPastReservationsByUserId(userId).subscribe((response: any) => {
      this.pastReservations = response;
    });
  }

}
