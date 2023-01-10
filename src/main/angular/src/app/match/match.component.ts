import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login/login.service";


@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  constructor(
    public loginService: LoginService
  ) {

  }

  ngOnInit(): void {
  }

}
