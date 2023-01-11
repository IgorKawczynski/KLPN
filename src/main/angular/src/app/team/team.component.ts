import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { StudentPlayer } from '../student/student-player';
import { TeamCreateDto } from './team-create-dto';
import { TeamService } from './team.service';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.scss']
})
export class TeamComponent implements OnInit {

  player: StudentPlayer = new StudentPlayer;
  players: StudentPlayer[] = [];
  team: TeamCreateDto = new TeamCreateDto;
  positions: string[] = [];

  selectedposition: string = '';

  isreferee: boolean = false;

  constructor(
    private teamService: TeamService,
    private messageService: MessageService,
  ) { }

  addPlayer(){
    this.players.push(this.player);
    // this.team.players.push(this.player);
    console.log('ilosc plikarzy po dodaniu: ' + this.players.length);
  }

  removePlayer(player: StudentPlayer){
    this.players = this.players.filter(e => e != e);
    console.log('ilosc plikarzy po odjeciu: ' + this.players.length);
  }

  ngOnInit(): void {
    this.positions = ['Napastnik', 'Pomocnik', 'Obrońca', 'Bramkarz']
  }

  btnDecline() {
    this.players.splice(0);
    this.messageService.add({life: 8000, severity:'info', summary:'Rejestracja drużyny', detail:'Anulowano rejestrację drużyny'});
  }

  btnConfirm() {
    
  }

}
