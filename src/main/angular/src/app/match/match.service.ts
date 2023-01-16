import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatchStatisticResponseDTO } from '../match-edit/match-statistic-response-dto';
import { environment } from 'src/environments/environment';
import { MatchForRefereeResponseDTO } from '../referee-matches/matches-for-referee-response';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getMatchStatisticsForMatchId(matchId: number): Observable<MatchStatisticResponseDTO[]> {
    return this.http.get<MatchStatisticResponseDTO[]>(`${this.apiServerUrl}/api/match-statistics/list-for-match/${matchId}`)
  }

  public getMatchForReferee(matchId: number): Observable<MatchForRefereeResponseDTO> {
    return this.http.get<MatchForRefereeResponseDTO>(`${this.apiServerUrl}/api/match/for-referee/${matchId}`);
  }

}
