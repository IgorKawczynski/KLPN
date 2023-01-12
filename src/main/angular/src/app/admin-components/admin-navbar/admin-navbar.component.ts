import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AdminNavbarComponent implements OnInit {
  items: MenuItem[] = [];

  constructor() { }

  ngOnInit(): void {

    this.items = [
      {label: 'Akceptuj',
      styleClass: 'admin-navbar-item',
        items:[
          {
            label:'Zaakceptuj drużynę',
            styleClass: 'admin-navbar-subitem',
            icon:'pi pi-users',
            routerLink: "/admin-accept-team"
          },
          {
            label:'Zaakceptuj zawodnika',
            styleClass: 'admin-navbar-subitem',
            icon:'pi pi-user-plus',
            routerLink: "/admin-accept-player"
          },
          {
            label:'Zaakceptuj prośbę o zmianę terminu meczu',
            styleClass: 'admin-navbar-subitem',
            icon:'pi pi-calendar-plus',
            routerLink: "/"
          }
            ]
      },
      {label: 'Przypisz',
      styleClass: 'admin-navbar-item',
        items:[
          {
            label: 'Przypisz sędziego do meczu',
            styleClass: 'admin-navbar-subitem',
            icon: 'pi pi-stopwatch',
            routerLink: "/"
          },
          {
            label: 'Przypisz drużyny do meczu',
            styleClass: 'admin-navbar-subitem',
            icon: 'pi pi-users',
            routerLink: "/"
          },
        ]},
        {label: 'Edytuj',
        styleClass: 'admin-navbar-item',
        items:[
          {
            label:'Edytuj termin meczu',
            styleClass: 'admin-navbar-subitem',
            icon:'pi pi-calendar-plus',
            routerLink: "/"
          },
          {
            label:'Edytuj rezerwację',
            styleClass: 'admin-navbar-subitem',
            icon:'pi pi-file-edit',
            routerLink: "/"
          },
          ]
      },
      {label: 'Powiadomienia', routerLink: "/", styleClass: 'admin-navbar-item'},
      {label: 'Harmonogram', routerLink: "/", styleClass: 'admin-navbar-item'},
      {label: 'Tabela', routerLink: "/", styleClass: 'admin-navbar-item'}
      ];

  }

}
