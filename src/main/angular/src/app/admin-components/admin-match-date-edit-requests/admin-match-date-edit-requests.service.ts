import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { MatchDateEditResponseDTO } from './match-date-edit-response';

@Injectable({
  providedIn: 'root'
})
export class AdminMatchDateEditRequestsService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getMatchDateEditRequests(): Observable<MatchDateEditResponseDTO[]> {
    return this.http.get<MatchDateEditResponseDTO[]>(`${this.apiServerUrl}/api/admin/matches-date-change-requests`)
  }

  // mde - match date edit
  public acceptMatchDateRequests(listOfMdeId: number[]): Observable<number[]> {
    return this.http.patch<number[]>(`${this.apiServerUrl}/api/admin/accept-match-date-changes`, listOfMdeId);
  }

  public rejectMatchDateRequests(listOfMdeId: number[]): Observable<number[]> {
    return this.http.post<number[]>(`${this.apiServerUrl}/api/admin/reject-match-date-changes`, listOfMdeId);
  }
  
}
