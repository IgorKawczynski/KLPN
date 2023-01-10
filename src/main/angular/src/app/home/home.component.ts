import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login/login.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  model: any = {};

  constructor(
    public loginService: LoginService
  ) {

  }

  ngOnInit(): void {
    // TAK SIE KORZYSTA Z FUNKCJI *CZY KTOŚ JEST ZALOGOWANY* + w HTMLU do tagu atrybut : ngIf="isLogged()"
    this.loginService.isLogged();
  }

}
