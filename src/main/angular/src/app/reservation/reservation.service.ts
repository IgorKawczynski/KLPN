import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ReservationRequestDto } from './reservation-request';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public createReservation(reservationDto: ReservationRequestDto): Observable<Object> {
    return this.http.post<ReservationRequestDto>(`${this.apiServerUrl}/api/reservation`, reservationDto);
  }

}
