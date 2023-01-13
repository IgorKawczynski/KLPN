import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MatchResponseDTO } from './matchResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getMatchesByDay(day: Date): Observable<MatchResponseDTO[]> {
    return this.http.post<MatchResponseDTO[]>(`${this.apiServerUrl}/api/match/for-day`, day);
  }

}
