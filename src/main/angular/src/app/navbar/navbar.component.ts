import {Component, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {LoginService} from "../login/login.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  items: MenuItem[ ] = [];
  itemsRight: MenuItem[ ] = [];
  teamId: number = 0;

  constructor(
    public loginService: LoginService
  ) {
  }


  ngOnInit(): void {
    if (this.loginService.isLogged()) {
      if(this.loginService.isAdmin()) {
        this.items = [
          {label: 'Panel administratora', routerLink: "/admin"},
          {
            label: 'Rezerwacja',
            items: [
              {
                label: 'Rezerwuj boisko',
                icon: 'pi pi-fw pi-calendar',
                routerLink: "/reservation"
              },
              {
                label: 'Zarządzaj rezerwacją',
                icon: 'pi pi-fw pi-cog',
                routerLink: "/reservation-list"
              },
              {
                label: 'Historia rezerwacji',
                icon: 'pi pi-fw pi-clock',
                routerLink: "/reservation-history"
              }
            ]
          },
          {label: 'Tabela', routerLink: "/table"},
          {label: 'Harmonogram', routerLink: "/schedule"},
          {label: 'Kontakt', routerLink: "/contact"}   
        ];
        this.itemsRight = [
          {label: 'Administrator ' + this.loginService.getName() || undefined, routerLink: "/users/my-profile"}
        ]
      }
      else if (this.loginService.isStudent()) {
        if (this.loginService.getRole() == 'CAPTAIN') {
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
                  label: 'Zarządzaj rezerwacją',
                  icon: 'pi pi-fw pi-cog',
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
                  label: 'Zarządzaj drużyną',
                  icon: 'pi pw-fw pi-user-edit',
                  routerLink: "/student/my-team"
                },
                {
                  label: 'Transfer',
                  icon: 'pi pw-fw pi-arrow-right-arrow-left',
                  routerLink: "/transfer"
                },
                {
                  label: 'Lista Transferowa',
                  icon: 'pi pi-fw pi-calendar',
                  routerLink: "/transfer-list"
                },
              ]
            },
            {label: 'Moje mecze', routerLink: "/student-matches"},
            {label: 'Tabela', routerLink: "/table"},
            {label: 'Harmonogram', routerLink: "/schedule"},
            {label: 'Kontakt', routerLink: "/contact"}
          ];
          this.itemsRight = [
            {label: 'Kapitan ' + this.loginService.getName() || undefined, routerLink: "/users/my-profile"}
          ]
        } else if (this.loginService.isReferee()) {
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
                  label: 'Zarządzaj rezerwacją',
                  icon: 'pi pi-fw pi-cog',
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
                  label: 'Lista Transferowa',
                  icon: 'pi pi-fw pi-calendar',
                  routerLink: "/transfer-list"
                },
              ]
            },
            {label: 'Panel Sędziego', routerLink: "/referee-matches"},
            {label: 'Moje mecze', routerLink: "/student-matches"},
            {label: 'Tabela', routerLink: "/table"},
            {label: 'Harmonogram', routerLink: "/schedule"},
            {label: 'Kontakt', routerLink: "/contact"}
          ];
          this.itemsRight = [
            {label: 'Sędzia ' + this.loginService.getName() || undefined, routerLink: "/users/my-profile"}
          ]
        } else {
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
                  label: 'Zarządzaj rezerwacją',
                  icon: 'pi pi-fw pi-cog',
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
                  label: 'Lista Transferowa',
                  icon: 'pi pi-fw pi-calendar',
                  routerLink: "/transfer-list"
                },
              ]
            },
            {label: 'Moje mecze', routerLink: "/student-matches"},
            {label: 'Tabela', routerLink: "/table"},
            {label: 'Harmonogram', routerLink: "/schedule"},
            {label: 'Kontakt', routerLink: "/contact"}
          ];
          this.itemsRight = [
            {label: 'Zawodnik ' + this.loginService.getName() || undefined, routerLink: "/users/my-profile"}
          ]
        }
      } else {
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
                label: 'Zarządzaj rezerwacją',
                icon: 'pi pi-fw pi-cog',
                routerLink: "/reservation-list"
              },
              {
                label: 'Historia rezerwacji',
                icon: 'pi pi-fw pi-clock',
                routerLink: "/reservation-history"
              }
            ]
          },
          {label: 'Lista Transferowa', routerLink: "/transfer-list"},
          {label: 'Tabela', routerLink: "/table"},
          {label: 'Harmonogram', routerLink: "/schedule"},
          {label: 'Kontakt', routerLink: "/contact"}
        ];
        this.itemsRight = [
          {label: 'Użytkownik ' + this.loginService.getName() || undefined, routerLink: "/users/my-profile"}
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
        {label: 'Lista Transferowa', routerLink: "/transfer-list"},
        {label: 'Kontakt', routerLink: "/contact"}
      ];
    }
  }
}
