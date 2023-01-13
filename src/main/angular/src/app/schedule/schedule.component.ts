import {Component, Injectable, OnInit} from '@angular/core';
import {TreeNode} from 'primeng/api';
import * as Http from "http";
import {formatDate} from '@angular/common';
import { ScheduleService } from './schedule.service';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})


export class ScheduleComponent implements OnInit {

  schedule: TreeNode[] = [];
  selectedNode: TreeNode | undefined;

  dates: Date[] = [];

  cols: any[] = [];
  constructor(private scheduleService: ScheduleService) { }

  ngOnInit() {
    this.getDates();

    console.log(this.dates);

    this.schedule = [];
    // for(let i = 1; i <= this.datesDTO.dates.length; i++) {
    //   let node = {
    //     data:{
    //       date: this.datesDTO.dates
    //     },
    //     children: [
    //       {
    //         data: {
    //           team1: 'Kortowskie szczury',
    //           team2: 'Dzikie ify',
    //           hour: '17.00',
    //           pitch_number: 3,
    //         }
    //       }
    //     ]
    //   };

    //   this.schedule.push(node);
    // }

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
      console.log(response);
    })
    console.log(this.dates);
  }


  public btnDelete(){
    console.log();
  }
}
