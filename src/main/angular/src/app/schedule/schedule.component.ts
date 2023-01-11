import {Component, Injectable, OnInit} from '@angular/core';
import {TreeNode} from 'primeng/api';
import * as Http from "http";
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})


export class ScheduleComponent implements OnInit {

  files: TreeNode[] = [];
  //data = formatDate(Date.now(),'dd-MM-yyyy','en-US');
  selectedNode: TreeNode | undefined;
  cols: any[] = [];
  constructor() { }

  ngOnInit() {
    this.files = [];
    for(let i = 1; i <= 7; i++) {
      let node = {
        data:{
          date: 'Data' + i,
        },
        children: [
          {
            data: {
              team1: 'Kortowskie szczury',
              team2: 'Dzikie ify',
              hour: '17.00',
              pitch_number: 3,
            }
          },
          {
            data: {
              team1: 'Kortowskie szczury',
              team2: 'Dzikie ify',
              hour: '17.00',
              pitch_number: 3,
            }
          },
          {
            data: {
              team1: 'Kortowskie szczury',
              team2: 'Dzikie ify',
              hour: '17.00',
              pitch_number: 3,
            }
          },
          {
            data: {
              team1: 'Kortowskie szczury',
              team2: 'Dzikie ify',
              hour: '17.00',
              pitch_number: 3,
            }
          },

          {
            data: {
              team1: 'Kozaki z Olsztyna',
              team2: 'Zwierzęta',
              hour: '17.00',
              pitch_number: 1,
            }
          },
        ]
      };

      this.files.push(node);
    }

    this.cols = [
      { field: 'date', header: 'Data' },
      { field: 'team1', header: 'Pierwsze drużyna' },
      { field: 'team2', header: 'Druga druzyna' },
      { field: 'hour', header: 'Godzina spotkania' },
      { field: 'pitch_number', header: 'Numer boiska' }
    ];
  }

}
