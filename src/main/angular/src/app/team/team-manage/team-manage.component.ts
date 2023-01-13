import { Component, OnInit } from '@angular/core';
import {PlayersInTeamDTO} from "./playersInTeamDTO";
import {TeamManageService} from "./team-manage.service";

@Component({
  selector: 'app-team-manage',
  templateUrl: './team-manage.component.html',
  styleUrls: ['./team-manage.component.scss']
})
export class TeamManageComponent implements OnInit {

  public players: PlayersInTeamDTO[] = [];

  public selectedPlayers: PlayersInTeamDTO[] = [];

  public selectedPlayersId: number[] = [];
  constructor(private teamManageService: TeamManageService) { }

  ngOnInit(): void {
    this.getPlayers();
  }

  public getPlayers(): void {
    this.teamManageService.getPlayers().subscribe((response: any) =>{
      this.players = response;
    })
  }
}
