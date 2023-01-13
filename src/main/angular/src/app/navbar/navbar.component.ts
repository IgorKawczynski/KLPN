import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {LoginService} from "../login/login.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  items: MenuItem[ ] = [ ];
  itemsRight: MenuItem[ ] = [ ];
  teamId: number = 0;

  constructor(
    public loginService: LoginService
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
                icon: 'pi pw-fw pi-user-edit',
                routerLink: "TU ID DRUZYNY AKTUALNEJ DLA UZYTKOWNIKA< JESLI JEJ NIE MA TO PTOAST"
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
                icon: 'pi pw-fw pi-user-edit',
                routerLink: "/team-edit"
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
}
