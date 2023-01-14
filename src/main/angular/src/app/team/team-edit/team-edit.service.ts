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

  // do zmiany requestBodi
  public getTeam(id: any): Observable<TeamDTO> {
    return this.http.post<TeamDTO>(`${this.apiServerUrl}/api/student/my-team/${id}`, null);
  }

  public deletePlayerFromTeam(studentId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/api/student/student-edit${studentId}`)
  }

  // public getTeamDetail(teamId: number): Observable<PlayerStatsDTO[]>{
  //   return this.http.get<PlayerStatsDTO[]>(`${this.apiServerUrl}/api/teams/details/${teamId}`);
  // }

  // public updateTeam(teamUpdateDto: TeamUpdateDto): Observable<Object> {
  //   return this.http.patch<ReservationUpdateDto>(`${this.apiServerUrl}/api/student/my-team/{studentId}`, reservationUpdateDto);
  // }
}
