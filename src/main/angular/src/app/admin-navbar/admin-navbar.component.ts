import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.scss']
})
export class AdminNavbarComponent implements OnInit {
  items: MenuItem[] = [];

  constructor() { }

  ngOnInit(): void {
    
    this.items = [
      {label: 'Akceptuj',
        items:[
          {
            label:'Zaakceptuj drużynę',
            icon:'pi pi-users',
            routerLink: "/"
          },
          {
            label:'Zaakceptuj zawodnika',
            icon:'pi pi-user-plus',
            routerLink: "/"
          },
          {
            label:'Zaakceptuj prośbę o zmianę terminu meczu',
            icon:'pi pi-calendar-plus',
            routerLink: "/"
          }
            ]
      },
      {label: 'Przypisz',
        items:[
          {
            label: 'Przypisz sędziego do meczu',
            icon: 'pi pi-stopwatch',
            routerLink: "/"
          },
          {
            label: 'Przypisz drużyny do meczu',
            icon: 'pi pi-users',
            routerLink: "/"
          },
        ]},
        {label: 'Edytuj',
        items:[
          {
            label:'Edytuj termin meczu',
            icon:'pi pi-calendar-plus',
            routerLink: "/"
          },
          {
            label:'Edytuj rezerwację',
            icon:'pi pi-file-edit',
            routerLink: "/"
          },
          ]
      },
      {label: 'Powiadomienia', routerLink: "/"},
      {label: 'Harmonogram', routerLink: "/"},
      {label: 'Tabela', routerLink: "/"}
      ];

  }

}
