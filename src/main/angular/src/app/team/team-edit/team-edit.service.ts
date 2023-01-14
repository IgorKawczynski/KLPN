import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TeamDTO} from "../team-dto";
import {PlayerStatsDTO} from "../team-detail/PlayerStatsDTO";

@Injectable({
  providedIn: 'root'
})
export class TeamEditService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTeam(id: any): Observable<TeamDTO> {
    return this.http.post<TeamDTO>(`${this.apiServerUrl}/api/student/my-team/${id}`, null);
  }

  public deletePlayerFromTeam(studentId: number): Observable<void> {
    return this.http.patch<void>(`${this.apiServerUrl}/api/student/my-team/edit/${studentId}`, null)
  }
}
