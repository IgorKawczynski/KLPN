import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.scss']
})
export class TeamComponent implements OnInit {

  models: any = {};
  player: any[] = [];
  positions: string[] = [];

  selectedposition: string = ''

  isreferee: boolean = false

  add(){
    this.player.push(this.models.index + '   ' + this.selectedposition);
    this.models.index = '';
    this.selectedposition = '';
  }

  remove(player: any[]){
    this.player = this.player.filter(e => e != player);
  }


  constructor() { }

  ngOnInit(): void {
    this.positions = ['Napastnik', 'Pomocnik', 'Obrońca', 'Bramkarz']
  }
}
