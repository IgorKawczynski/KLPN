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
    console.log("Is actually logged user an admin : " + (localStorage.getItem("isAdmin") ===  "true"));
    return localStorage.getItem("isAdmin") ===  "true"
  }

  public isStudent() {
    console.log("Is actually logged user a student? : " + (localStorage.getItem("isStudent") ===  "true"));
    return localStorage.getItem("isStudent") ===  "true"
  }

  public getName() {
    return localStorage.getItem("name");
  }

  public getId() {
    return Number(localStorage.getItem("id"));
  }

}
