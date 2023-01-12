import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ReservationResponseDTO } from '../reservation-list/reservation-response';

@Injectable({
  providedIn: 'root'
})
export class ReservationHistoryService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getPastReservationsByUserId(userId: number): Observable<ReservationResponseDTO[]> {
    return this.http.get<ReservationResponseDTO[]>(`${this.apiServerUrl}/api/reservation/list-history/${userId}`)
  }
}
