import { Component, OnInit } from '@angular/core';
import {TeamDetailService} from "./team-detail.service";
import {playerAndStatsDTOs} from "./playerAndStatsDTOs";
import { LoginService } from '../../login/login.service';
@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.scss']
})
export class TeamDetailComponent implements OnInit {

  public playerstats: playerAndStatsDTOs[] = [];
  constructor(private loginService: LoginService, private TeamDetailService: TeamDetailService) { }

  ngOnInit(): void {
  }


}
