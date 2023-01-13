import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {TeamDetailComponent} from "./team-detail.component";
import {PlayerStatsDTO} from "./PlayerStatsDTO";

@Injectable({
  providedIn: 'root'
})
export class TeamDetailService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  public getTeamDetail(teamId: number): Observable<PlayerStatsDTO[]>{
    return this.http.get<PlayerStatsDTO[]>(`${this.apiServerUrl}/api/teams/details/${teamId}`);
  }


}
