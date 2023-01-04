import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public isLogged() {
    return sessionStorage.length > 0;
  }

}
