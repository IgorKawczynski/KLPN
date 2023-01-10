import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login/login.service";


@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  constructor(
    public loginService: LoginService
  ) {

  }

  ngOnInit(): void {
  }

}
