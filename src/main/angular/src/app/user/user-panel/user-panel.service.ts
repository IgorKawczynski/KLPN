import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserEditDTO} from "./user-edit-dto";
import {LoginService} from "../../login/login.service";

@Injectable({
  providedIn: 'root'
})
export class UserPanelService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(
    private http: HttpClient,
    public loginService: LoginService) { }

  public putUser(user: UserEditDTO): Observable<Object> {
    return this.http.put<any>(`${this.apiServerUrl}/api/user/my-profile?id=${this.loginService.getId()}`, user);
  }

  public getUserEditDTOById(): Observable<UserEditDTO>{
    return this.http.get<UserEditDTO>(`${this.apiServerUrl}/api/user/me?id=${this.loginService.getId()}`);
  }

  public deleteUserById(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/api/user/${userId}`);
  }
}
