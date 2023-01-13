import { Component, OnInit } from '@angular/core';
import {TeamDetailService} from "./team-detail.service";
import {PlayerStatsDTO} from "./PlayerStatsDTO";
import {LoginService} from '../../login/login.service';
import {ActivatedRoute, Router} from "@angular/router";
@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.scss']
})
export class TeamDetailComponent implements OnInit {

  public playerstats: PlayerStatsDTO[] = [];
  teamName: string = "";
  id: any;

  constructor(
    private loginService: LoginService,
    private teamDetailService: TeamDetailService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.params['id'];
    this.getDetail();
    console.log(this.id);
    console.log(this.teamName);
  }

  public getDetail() {
    this.teamDetailService
      .getTeamDetail(this.id)
      .subscribe( (response: any) => {
        this.playerstats = response.playerAndStatsDTOs;
        this.teamName = response.Name;
        console.log(response);
        console.log(this.playerstats);
      });
  }
}
