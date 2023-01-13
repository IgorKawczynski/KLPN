import { Component, OnInit} from '@angular/core';
import { MatchResponseDTO } from './matchResponseDTO';
import { ScheduleService } from './schedule.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})


export class ScheduleComponent implements OnInit {

  public chosenDate = new Date;
  public date?: String;

  public matches: MatchResponseDTO[] = [];

  cols: any[] = [];
  constructor(private scheduleService: ScheduleService) { }

  ngOnInit(): void {
    this.chosenDate = new Date(this.chosenDate.setHours(0, 0 ,0));
    this.date = this.chosenDate.toLocaleDateString();

    this.cols = [
      { field: 'date', header: 'Data' },
      { field: 'team1', header: 'Pierwsza drużyna' },
      { field: 'team2', header: 'Druga drużyna' },
      { field: 'hour', header: 'Godzina spotkania' },
      { field: 'pitch_number', header: 'Numer boiska' },
      { field: 'result', header: 'Wynik' },
      { field: 'referee', header: 'Sędzia' }
    ];

  }

  public getMatches(): void {
    this.scheduleService.getMatchesByDay(this.chosenDate).subscribe((response: any) =>{
      this.matches = response;
    });
  }

  public showMatches() {
    this.getMatches();
    this.date = this.chosenDate.toLocaleDateString();
  }

}
