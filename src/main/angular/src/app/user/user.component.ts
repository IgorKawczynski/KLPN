import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login/login.service";


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  constructor(
    public loginService: LoginService
  ){

  }


  ngOnInit(): void {
  }

}
