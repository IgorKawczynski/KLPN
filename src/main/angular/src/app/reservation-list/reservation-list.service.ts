import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

import { ReservationResponseDTO } from './reservation-response';

@Injectable({
  providedIn: 'root'
})
export class ReservationListService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getReservationsByUserId(userId: number): Observable<ReservationResponseDTO[]> {
    return this.http.get<ReservationResponseDTO[]>(`${this.apiServerUrl}/api/reservation/list/${userId}`)
  }

  public deleteReservationById(reservationId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/api/reservation/${reservationId}`)
  }
}
