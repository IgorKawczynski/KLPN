import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";
import {LoginService} from "../login/login.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  model: any = {};

  constructor(public loginService: LoginService) { }


  ngOnInit(): void {
  }

  homePanelActivate() {
    const home = document.querySelector('.home');
    // nie usuwać komentarza niżej -> to adnotacja typescriptowa!!
    // @ts-ignore
    home.classList.toggle('home--teams');
    console.log("Animacja")
  }

}
