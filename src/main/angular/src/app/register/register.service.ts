import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import {UserRequestDTO} from "../user/user-request";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public register(user: UserRequestDTO): Observable<Object> {
    return this.http.post<UserRequestDTO>(`${this.apiServerUrl}/api/user/register`, user);
  }

}
