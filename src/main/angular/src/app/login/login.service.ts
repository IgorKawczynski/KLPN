import { Injectable } from '@angular/core';
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private router: Router,
    private messageService: MessageService
  ) { }

  public isLogged() {
    return sessionStorage.length > 0;
  }

  public isAdmin() {
    return localStorage.getItem("isAdmin") ===  "true"
  }

  public isStudent() {
    return localStorage.getItem("isStudent") ===  "true"
  }

  public isCaptain() {
    return localStorage.getItem("role") === "CAPTAIN"
  }

  public isReferee() {
    return localStorage.getItem("role") === "REFEREE"
  }

  public getName() {
    return localStorage.getItem("name");
  }

  public getRole() {
    return localStorage.getItem("role");
  }

  public getId() {
    return Number(localStorage.getItem("id"));
  }


  public btnLogout() {
    this.logout();
  }

  public logout() {
    if(sessionStorage.length > 0){
      sessionStorage.removeItem('token')
      this.router.navigateByUrl('/login').then(r => null);
      this.messageService.add({life:3000, severity:'success', summary:'Wyloguj', detail:" Pomyślnie wylogowano!"})
    }
    else {
      this.messageService.add({life:3000, severity:'info', summary:'Wyloguj', detail:" Najpierw się musisz zalogować!"})
    }
  }


}
