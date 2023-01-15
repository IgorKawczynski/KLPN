import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ReservationResponseDTO } from '../reservation-list/reservation-response';import { ReservationUpdateDto } from './reservation-update-dto';

@Injectable({
  providedIn: 'root'
})
export class ReservationEditService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public updateReservation(reservationUpdateDto: ReservationUpdateDto): Observable<Object> {
    return this.http.patch<ReservationUpdateDto>(`${this.apiServerUrl}/api/reservation`, reservationUpdateDto);
  }

  public getReservationById(id: number): Observable<ReservationResponseDTO> {
    return this.http.get<ReservationResponseDTO>(`${this.apiServerUrl}/api/reservation/response/${id}`)
  }

}
