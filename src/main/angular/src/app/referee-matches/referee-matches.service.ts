import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MatchForRefereeResponseDTO } from './matches-for-referee-response';

@Injectable({
  providedIn: 'root'
})
export class RefereeMatchesService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getMatchesForReferee(refereeId: number): Observable<MatchForRefereeResponseDTO[]> {
    return this.http.post<MatchForRefereeResponseDTO[]>(`${this.apiServerUrl}/api/match/referee-matches`, refereeId);
  }
}
