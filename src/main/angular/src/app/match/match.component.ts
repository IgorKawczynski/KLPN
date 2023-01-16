import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatchStatisticResponseDTO } from '../match-edit/match-statistic-response-dto';
import { MatchForRefereeResponseDTO } from '../referee-matches/matches-for-referee-response';
import { MatchService } from './match.service';

@Component({
  selector: 'app-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.scss']
})
export class MatchComponent implements OnInit {

  public matchStatistics: MatchStatisticResponseDTO[] = [];
  public thisMatch: MatchForRefereeResponseDTO = new MatchForRefereeResponseDTO;

  constructor(
    private _Activatedroute: ActivatedRoute,
    private matchService: MatchService,
  ) { }

  ngOnInit(): void {
    this.getData();
  }

  public getMatchStatisticsForMatchId(): void {
    let matchId: number = this._Activatedroute.snapshot.params['id'];

    this.matchService.getMatchStatisticsForMatchId(matchId).subscribe((response: any) =>{
      this.matchStatistics = response;
    });
  }

  public getMatch(): void {
    let matchId: number = this._Activatedroute.snapshot.params['id'];

    this.matchService.getMatchForReferee(matchId).subscribe((response: any) =>{
      this.thisMatch = response;
    })
  }

  public getData(): void {
    this.getMatch();
    this.getMatchStatisticsForMatchId();
  }

}
