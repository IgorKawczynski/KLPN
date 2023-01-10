import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserUpdateToStudentDto } from './update-to-student/update-to-student';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public updateToStudent(updateToStudent: UserUpdateToStudentDto): Observable<Object> {
    return this.http.post<UserUpdateToStudentDto>(`${this.apiServerUrl}/api/user/update-to-student`, updateToStudent);
  }
  
}
