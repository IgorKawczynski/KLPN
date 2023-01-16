import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login/login.service';
import { MatchForRefereeResponseDTO } from './matches-for-referee-response';
import { RefereeMatchesService } from './referee-matches.service';

@Component({
  selector: 'app-referee-matches',
  templateUrl: './referee-matches.component.html',
  styleUrls: ['./referee-matches.component.scss']
})
export class RefereeMatchesComponent implements OnInit {

  public refereeMatches: MatchForRefereeResponseDTO[] = [];
  public matchId!: number;

  constructor(
    private loginService: LoginService,
    private refereeMatchesService: RefereeMatchesService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getMatches();
  }

  public getMatches(): void {
    let refereeId: number = this.loginService.getId();
    this.refereeMatchesService.getMatchesForReferee(refereeId).subscribe((response: any) =>{
      this.refereeMatches = response;
    })
  }

  public btnEdit(matchId: number){
    this.router.navigateByUrl(`/match-edit/${matchId}`);
  }

}
