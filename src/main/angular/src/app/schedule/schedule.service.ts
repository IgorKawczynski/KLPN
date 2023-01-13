import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getDatesOfMatches(): Observable<Date[]> {
    return this.http.get<Date[]>(`${this.apiServerUrl}/api/match/dates`)
  }


}
