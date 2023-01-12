import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() { }

  public isLogged() {
    return sessionStorage.length > 0;
  }

}
