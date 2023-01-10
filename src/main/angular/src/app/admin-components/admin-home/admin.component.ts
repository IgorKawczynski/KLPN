import { Component, OnInit } from '@angular/core';
import {LoginService} from "../../login/login.service";


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor(
    public loginService: LoginService
  ) {

  }

  ngOnInit(): void {
  }

}
