import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TeamCreateDto } from '../team/team-create-dto';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public registerTeam(team : TeamCreateDto): Observable<TeamCreateDto> {
    return this.http.post<TeamCreateDto>(`${this.apiServerUrl}/api/teams`, team);
  }
  
}
