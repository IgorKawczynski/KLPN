import { Component, OnInit } from '@angular/core';
import {MenuItem} from "primeng/api";
import {LoginService} from "../login/login.service";
import {TeamDTO} from "../team/team-dto";
import {TeamEditService} from "../team/team-edit/team-edit.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  model: any = {};
  teamId: any;

  constructor(
    public loginService: LoginService,
    private teamEditService: TeamEditService,
    private router: Router) { }


  ngOnInit(): void {
    if(this.loginService.isStudent()) {
      this.getTeam();
    }
  }

  homePanelActivate() {
    const home = document.querySelector('.home');
    // nie usuwać komentarza niżej -> to adnotacja typescriptowa!!
    // @ts-ignore
    home.classList.toggle('home--teams');
    console.log("Animacja")
  }

  public getTeam(): void {
    let userId: number = this.loginService.getId();
    this.teamEditService.getTeam(userId).subscribe((response: any) => {
      this.teamId = response.teamId;
    });
  }

}
