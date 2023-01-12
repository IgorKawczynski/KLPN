import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { TeamsToAcceptDto } from './teamsToAcceptDto';

@Injectable({
  providedIn: 'root'
})
export class AdminAcceptTeamService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }


  public getTeamsToAccept(): Observable<TeamsToAcceptDto[]> {
    return this.http.get<TeamsToAcceptDto[]>(`${this.apiServerUrl}/api/teams/to-accept`)
  }

  public updateTeams(listOfTeamsId: number[]): Observable<number[]>{
    return this.http.patch<number[]>(`${this.apiServerUrl}/api/admin/accept-teams`, listOfTeamsId);
  }

  public rejectTeams(listOfTeamsId: number[]): Observable<number[]>{
    return this.http.post<number[]>(`${this.apiServerUrl}/api/admin/reject-teams`, listOfTeamsId);
  }
}
