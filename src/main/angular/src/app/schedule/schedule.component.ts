import {Component, OnInit} from '@angular/core';
import {TreeNode} from 'primeng/api';
import { ScheduleService } from './schedule.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})


export class ScheduleComponent implements OnInit {

  schedule: TreeNode[] = [];

  dates: Date[] = [];

  cols: any[] = [];
  constructor(private scheduleService: ScheduleService) { }

  ngOnInit() {
    this.getDates();
    this.setSchedule();

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

  public getDates(): void {
    this.scheduleService.getDatesOfMatches().subscribe((response: any) =>{
      this.dates = response;
    })
  }

  public setSchedule(): void {
    for(let i = 1; i <= 3; i++) {
      let node = 
      {
        data:
        {
          date: "this.dates[i]"
        },
        children: 
        [
          {
            data: 
            {
              team1: 'Kortowskie szczury',
              team2: 'Dzikie ify',
              hour: '17.00',
              pitch_number: 3,
              result: '3:3',
              referee: 'Janek Takitego'
            }
          }
        ]
      };
      this.schedule.push(node);
    }
  }


  public btnDelete(){
    console.log(this.dates);
  }
}
