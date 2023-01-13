import { Component, OnInit } from '@angular/core';
import {MenuItem, MessageService} from 'primeng/api';
import {LoginService} from "../login/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  items: MenuItem[ ] = [ ];

  itemsRight: MenuItem[ ] = [ ];

  constructor(public loginService: LoginService,
              private router: Router,
              private messageService: MessageService
  ) { }


  ngOnInit(): void {
    if(this.loginService.isLogged()) {
      if(this.loginService.isStudent()) {
        this.items = [
          {
            label: 'Rezerwacja',
            items: [
              {
                label: 'Rezerwuj boisko',
                icon: 'pi pi-fw pi-calendar',
                routerLink: "/reservation"
              },
              {
                label:'Zarządzaj rezerwacją',
                icon:'pi pi-fw pi-cog',
                routerLink: "/reservation-list"
              },
              {
                label: 'Historia rezerwacji',
                icon: 'pi pi-fw pi-clock',
                routerLink: "/reservation-history"
              }
            ]
          },
          {
            label: 'Drużyna',
            items: [
              {
                label: 'Zarejestruj drużynę',
                icon: 'pi pw-fw pi-pencil',
                routerLink: "/team"
              },
              {
                label: 'Zarządzaj drużyną',
                icon: 'pi pw-fw pi-user-edit'
              },
              {
                label: 'Transfer',
                icon: 'pi pw-fw pi-arrow-right-arrow-left'
              },
            ]
          },
          {label: 'Tabela', routerLink: "/table"},
          {label: 'Harmonogram', routerLink: "/schedule"},
          {label: 'Kontakt', routerLink: "/contact"}
        ];
        this.itemsRight =  [
          {label: 'Witaj ' + this.loginService.getName() || undefined, routerLink: "/users/my-profile"}
        ]
      }
      else {
        this.items = [
          {
            label: 'Rezerwacja',
            items: [
              {
                label: 'Rezerwuj boisko',
                icon: 'pi pi-fw pi-calendar',
                routerLink: "/reservation"
              },
              {
                label:'Zarządzaj rezerwacją',
                icon:'pi pi-fw pi-cog',
                routerLink: "/reservation-list"
              },
              {
                label: 'Historia rezerwacji',
                icon: 'pi pi-fw pi-clock',
                routerLink: "/reservation-history"
              }
            ]
          },
          {
            label: 'Drużyna',
            items: [
              {
                label: 'Zarejestruj drużynę',
                icon: 'pi pw-fw pi-pencil',
                routerLink: "/team"
              },
              {
                label: 'Zarządzaj drużyną',
                icon: 'pi pw-fw pi-user-edit'
              },
              {
                label: 'Transfer',
                icon: 'pi pw-fw pi-arrow-right-arrow-left'
              },
            ]
          },
          {label: 'Tabela', routerLink: "/table"},
          {label: 'Harmonogram', routerLink: "/schedule"},
          {label: 'Kontakt', routerLink: "/contact"}
        ];
        this.itemsRight =  [
          {label: 'Witaj ' + this.loginService.getName() || undefined, routerLink: "/users/my-profile"}
        ]
      }
    }
    else {
      this.items = [
        {
          label: 'Zaloguj się',
          routerLink: '/login'
        },
        {label: 'Tabela', routerLink: "/table"},
        {label: 'Harmonogram', routerLink: "/schedule"},
        {label: 'Kontakt', routerLink: "/contact"}
      ];
    }
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
