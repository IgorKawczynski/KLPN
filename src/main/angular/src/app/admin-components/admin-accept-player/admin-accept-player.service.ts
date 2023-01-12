import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { UserToAcceptDto } from './userToAcceptDto';

@Injectable({
  providedIn: 'root'
})
export class AdminAcceptPlayerService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getUsersToAccept(): Observable<UserToAcceptDto[]> {
    return this.http.get<UserToAcceptDto[]>(`${this.apiServerUrl}/api/user`)
  }

  public updateUsers(listOfUsersId: number[]): Observable<number[]> {
    return this.http.patch<number[]>(`${this.apiServerUrl}/api/admin/update-to-students`, listOfUsersId);
  }

  public rejectUsers(listOfUsersId: number[]): Observable<number[]> {
    return this.http.post<number[]>(`${this.apiServerUrl}/api/admin/reject-students`, listOfUsersId);
  }

}
