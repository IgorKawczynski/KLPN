import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() { }

  public isLogged() {
    return sessionStorage.length > 0;
  }

  public isAdmin() {
    return localStorage.getItem("isAdmin") ===  "true"
  }

  public isStudent() {
    return localStorage.getItem("isStudent") ===  "true"
  }

  public getName() {
    return localStorage.getItem("name");
  }

  public getRole() {
    return localStorage.getItem("role");
  }

  public getId() {
    return localStorage.getItem("id");
  }


}
