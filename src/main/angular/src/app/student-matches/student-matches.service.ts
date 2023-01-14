import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MatchForStudentResponseDTO } from './match-for-student';

@Injectable({
  providedIn: 'root'
})
export class StudentMatchesService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getMatchesForStudent(studentId: number): Observable<MatchForStudentResponseDTO[]> {
    return this.http.get<MatchForStudentResponseDTO[]>(`${this.apiServerUrl}/api/match/student-matches/${studentId}`)
  }
  
}
