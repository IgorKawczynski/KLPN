import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { MenuItem } from 'primeng/api';
import {LoginService} from "../../login/login.service";

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AdminNavbarComponent implements OnInit {

  items: MenuItem[] = [];
  itemsRight: MenuItem[ ] = [ ];

  constructor(
    public loginService: LoginService
  ) { }

  ngOnInit(): void {
    this.items = [
      {label: 'Strona główna', routerLink: "/", styleClass: 'admin-navbar-item'},

      {
       label:'Zaakceptuj zawodnika', styleClass: 'admin-navbar-item',
       routerLink: "/admin-accept-player"
      },

      {
       label:'Zaakceptuj prośbę o zmianę terminu meczu', styleClass: 'admin-navbar-item',
       routerLink: "/admin-accept-requests"
      },

      {
        label:'Zaakceptuj drużynę', styleClass: 'admin-navbar-item',
        routerLink: "/admin-accept-team"
      },

      ];
    this.itemsRight =  [
      {label: 'Witaj ' + this.loginService.getName() || undefined, routerLink: "/users/my-profile"}
    ]
  }

}
