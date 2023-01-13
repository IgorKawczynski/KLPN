import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import {playerAndStatsDTOs} from "./playerAndStatsDTOs";
import {TeamDetailComponent} from "./team-detail.component";

@Injectable({
  providedIn: 'root'
})
export class TeamDetailService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  public getTeamDetail(teamId: number): Observable<playerAndStatsDTOs[]>{
    return this.http.get<playerAndStatsDTOs[]>(`${this.apiServerUrl}/api/table"/details/1001`)
  }


}
