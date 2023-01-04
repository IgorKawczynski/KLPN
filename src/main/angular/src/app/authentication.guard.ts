import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(private router: Router, private messageService: MessageService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {


      let token = sessionStorage.getItem('token');


      if (state.url == "/login") {
        if(token){
          this.messageService.add({life:3000, severity:'info', summary:'Login', detail:" Już jesteś zalogowany ! "})
          return this.router.parseUrl('/');
        }
        return true;
      }

      if(!token) {
        let urlUserEdit = "/user/edit"
        let urlReservation = "/reservation"
        let urlContact = "/contact"
        // TODO -- DO DODANIA KOLEJNE
        if(state.url == urlUserEdit || state.url == urlReservation || state.url == urlContact) {
          console.log("!!! !!! NIE JESTES ZALOGOWANY !!! !!!")
          this.messageService.add({life:3500, severity:'info', summary:'Login', detail:" Musisz się najpierw zalogować ! "})
          return this.router.parseUrl('/login');
        }
        return true;
      }

    return true;
  }

}
