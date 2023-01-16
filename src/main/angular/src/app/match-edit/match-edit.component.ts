import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { mapToEnumValue } from './event';
import { MatchEditService } from './match-edit.service';
import { MatchStatisticCreateByIndexDTO } from './match-statistic-create-dto';
import { MatchStatisticResponseDTO } from './match-statistic-response-dto';
import { MatchForRefereeResponseDTO } from '../referee-matches/matches-for-referee-response';

@Component({
  selector: 'app-match-edit',
  templateUrl: './match-edit.component.html',
  styleUrls: ['./match-edit.component.scss']
})
export class MatchEditComponent implements OnInit {

  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  public matchStatistics: MatchStatisticResponseDTO[] = [];
  public events: string[] = [];
  public matchStatistic: MatchStatisticCreateByIndexDTO = new MatchStatisticCreateByIndexDTO;
  public thisMatch: MatchForRefereeResponseDTO = new MatchForRefereeResponseDTO;


  constructor(
    private _Activatedroute: ActivatedRoute,
    private matchEditService: MatchEditService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.getData();
    this.events = ['Goal', 'Asysta', 'Bramka samobójcza', 'Żółta kartka', 'Czerwona kartka']
  }

  public getMatchStatisticsForMatchId(): void {
    let matchId: number = this._Activatedroute.snapshot.params['id'];
    this.matchEditService.getMatchStatisticsForMatchId(matchId).subscribe((response: any) =>{
      this.matchStatistics = response;
    });
  }

  public btnDelete(rowId: number){
    this.deleteMatchStatisticById(rowId);
  }

  public btnAdd(){
    this.createMatchStatistic();
  }

  public deleteMatchStatisticById(rowId: number): void {
    this.matchEditService.deleteMatchStatisticById(rowId).subscribe((response: any) =>{
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 8000, severity:'success', summary:'Usunięto', detail:'Pomyślnie usunięto statysykę!'});
        setTimeout(location.reload.bind(location), 1500);
      }
    })
  }

  public createMatchStatistic() {

    if(this.matchStatistic.studentIndex == null || this.matchStatistic.studentIndex == undefined) {
      this.messageService.add({life:8000, severity:'error', summary:'Błąd', detail:'Proszę podać numer indeksu zawodnika.'});
      return;
    }
    if(this.matchStatistic.event == null || this.matchStatistic.event == undefined) {
      this.messageService.add({life:8000, severity:'error', summary:'Błąd', detail:'Proszę wybrać zdarzenie.'});
      return;
    }
    this.matchStatistic.matchId = this._Activatedroute.snapshot.params['id'];
    this.matchStatistic.event = mapToEnumValue(this.matchStatistic.event);
    this.matchEditService.createMatchStatisticByIndex(this.matchStatistic).subscribe((response: any) => {
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 8000, severity:'success', summary:'Dodano', detail:'Pomyślnie dodano statystykę!'});
        setTimeout(location.reload.bind(location), 1500);
      }
    })
  }

  public getMatch(): void {
    let matchId: number = this._Activatedroute.snapshot.params['id'];

    this.matchEditService.getMatchForReferee(matchId).subscribe((response: any) =>{
      this.thisMatch = response;
    })
  }

  public getData(): void {
    this.getMatch();
    this.getMatchStatisticsForMatchId();
  }
}
