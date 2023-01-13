import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

import {PlayersInTeamDTO} from "./playersInTeamDTO";

@Injectable({
  providedIn: 'root'
})
export class TeamManageService {
  private apiServerUrl = environment.apiBaseUrl;


  constructor(private http: HttpClient) { }

  public getPlayers(): Observable<PlayersInTeamDTO[]>{
    return this.http.get<PlayersInTeamDTO[]>(`${this.apiServerUrl}/api/teams/detail/{1054}`);
  }
}
