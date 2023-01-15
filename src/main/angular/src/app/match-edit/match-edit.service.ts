import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MatchStatisticCreateByIndexDTO } from './match-statistic-create-dto';
import { MatchStatisticResponseDTO } from './match-statistic-response-dto';
import { MatchForRefereeResponseDTO } from '../referee-matches/matches-for-referee-response';


@Injectable({
  providedIn: 'root'
})
export class MatchEditService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getMatchStatisticsForMatchId(matchId: number): Observable<MatchStatisticResponseDTO[]> {
    return this.http.get<MatchStatisticResponseDTO[]>(`${this.apiServerUrl}/api/match-statistics/list-for-match/${matchId}`)
  }

  public deleteMatchStatisticById(matchStatisticId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/api/match-statistics/${matchStatisticId}`)
  }

  public createMatchStatisticByIndex(matchStatisticCreateDto: MatchStatisticCreateByIndexDTO): Observable<Object> {
    return this.http.post<MatchStatisticCreateByIndexDTO>(`${this.apiServerUrl}/api/match-statistics/create-by-index`, matchStatisticCreateDto);
  }

  public getMatchForReferee(matchId: number): Observable<MatchForRefereeResponseDTO> {
    return this.http.get<MatchForRefereeResponseDTO>(`${this.apiServerUrl}/api/match/for-referee/${matchId}`);
  }
}
