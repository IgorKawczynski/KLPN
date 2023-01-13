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
      background-color: rgba(255, 204, 0, 0.61);
      color: #160621;
    }

    tr.second {
      background-color: rgba(185, 185, 185, 0.61);
      color: #160621;
    }

    tr.third {
      background-color: rgba(115, 54, 9, 0.61);
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
