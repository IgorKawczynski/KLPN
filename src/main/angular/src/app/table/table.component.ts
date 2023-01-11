import { Component, OnInit } from '@angular/core';
import {TeamRequest} from "./team-request";
import {TableService} from "./table.service";
import {TreeNode} from 'primeng/api';


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss',],
  styles: [
    `tr.first {
      background-color: rgba(255, 213, 28, 0.71);
    }

    tr.second {
      background-color: rgba(192, 192, 192, 0.71);
    }

    tr.third {
      background-color: rgba(139, 69, 19, 0.71);
      color: white;
    }

    tr.else {
      background-color: rgba(255, 255, 255, 0.8);
    }
    `
  ]

})
export class TableComponent implements OnInit {

  public teams: TeamRequest[] = [];


  constructor(private tableService: TableService) { }

  ngOnInit(): void {
    this.generateTable();
  }

  public generateTable(): void {
    this.tableService.getTeams().subscribe((response: any) =>{
      this.teams = response;
    })
  }

}
