import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { ReservationResponseDTO } from './reservation-response';
import { ReservationListService } from './reservation-list.service';
import { LoginService } from '../login/login.service';
import { Router } from '@angular/router';

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
    private loginService: LoginService,
    private router: Router
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

  public btnDelete(rowId: number){
    this.deleteReservationById(rowId);
  }

  public btnEdit(rowId: number){
    this.router.navigateByUrl(`/reservation-edit/${rowId}`);
  }

  public deleteReservationById(rowId: number): void {
    this.reservationListService.deleteReservationById(rowId).subscribe((response: any) =>{
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 8000, severity:'success', summary:'Usunięto', detail:'Pomyślnie usunięto rezerwację!'});
        setTimeout(location.reload.bind(location), 1500);
      }
    })
  }


}
