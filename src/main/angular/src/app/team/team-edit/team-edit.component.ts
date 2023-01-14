import {
  AfterContentChecked,
  AfterContentInit,
  AfterViewChecked,
  Component,
  DoCheck,
  OnChanges,
  OnInit, SimpleChanges
} from '@angular/core';
import {TeamEditService} from "./team-edit.service";
import {LoginService} from "../../login/login.service";
import {TeamDTO} from "../team-dto";
import {TeamDetailService} from "../team-detail/team-detail.service";
import {TeamDetailComponent} from "../team-detail/team-detail.component";
import {PlayerStatsDTO} from "../team-detail/PlayerStatsDTO";
import {Router} from "@angular/router";

@Component({
  selector: 'app-team-edit',
  templateUrl: './team-edit.component.html',
  styleUrls: ['./team-edit.component.scss']
})
export class TeamEditComponent implements OnInit {

  team: any;
  userId!: number;
  playerStats!: PlayerStatsDTO[];

  constructor(
    private teamEditService: TeamEditService,
    public loginService: LoginService,
    private router: Router) { }

  ngOnInit(): void {
    this.getTeam();
  }

  public btnRoute() : void {
    this.router.navigateByUrl('/transfer');
  }

  public btnDelete() : void {
    this.teamEditService.deleteXD;
  }

  public getTeam(): void {
    this.userId = this.loginService.getId();
    this.teamEditService.getTeam(this.userId).subscribe((response: TeamDTO) => {
      this.team = response;
      this.playerStats = response.playerAndStatsDTOs;
      console.log("TEAM :: ", this.team);
      console.log("TEAM ID :: ", this.team.teamId);
      console.log("TEAM NAME :: ", this.team.Name);
      console.log("PLAYERS :: ", this.playerStats);
    });
  }
}
