import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  items: MenuItem[]=[];

  constructor() { }


  ngOnInit(): void {

    this.items = [
      {label: 'Rezerwacja',
        items:[
          {
            label:'Rezerwuj boisko',
            icon:'pi pi-fw pi-calendar',
            routerLink: "/reservation"
          },
          {
            label:'Zarządzaj rezerwacją',
            icon:'pi pi-fw pi-cog',
            routerLink: "/reservation-list"
          },
          {
            label:'Historia rezerwacji',
            icon:'pi pi-fw pi-clock'
          }
            ]
      },
      {label: 'Drużyna',
        items:[
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
        ]},
      {label: 'Tabela', routerLink: "/table"},
      {label: 'Harmonogram', routerLink: "/schedule"},
      {label: 'Kontakt', routerLink: "/contact"}
      ];

  }

}
