import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {HttpClient} from "@angular/common/http";
import { Observable } from 'rxjs';
import {TeamRequest} from "./team-request";
@Injectable({
  providedIn: 'root'
})
export class TableService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getTeams(): Observable<TeamRequest[]>{
    return this.http.get<TeamRequest[]>(`${this.apiServerUrl}/api/teams/table`)
  }
}
