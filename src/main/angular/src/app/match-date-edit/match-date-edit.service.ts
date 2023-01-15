import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MatchForStudentResponseDTO } from '../student-matches/match-for-student';

@Injectable({
  providedIn: 'root'
})
export class MatchDateEditService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getMatchToEdit(reservationId: number): Observable<MatchForStudentResponseDTO> {
    return this.http.get<MatchForStudentResponseDTO>(`${this.apiServerUrl}/api/match/to-edit/${reservationId}`)
  }

}
