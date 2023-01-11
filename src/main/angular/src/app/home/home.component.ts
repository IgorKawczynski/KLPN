import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  model: any = {};

  constructor() { }


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
