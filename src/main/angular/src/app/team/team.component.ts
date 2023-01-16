import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { mapToEnumValue, Position } from '../student/position-enum';
import { StudentPlayer } from '../student/student-player';
import { StudentPlayerDTO } from '../student/student-player-dto';
import { StudentService } from '../student/student.service';
import { TeamCreateDto } from './team-create-dto';
import { TeamService } from './team.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.scss']
})
export class TeamComponent implements OnInit {

  player: StudentPlayerDTO = new StudentPlayerDTO;
  players: StudentPlayer[] = [];
  team: TeamCreateDto = new TeamCreateDto;
  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  positions: string[] = [];

  selectedposition: string = '';
  isreferee: boolean = false;

  constructor(
    private messageService: MessageService,
    private studentService: StudentService,
    private router: Router
  ) { }

  addPlayer(): void {
    if(this.player.indexNumber == null || this.player.indexNumber == undefined) {
      this.messageService.add({life:8000, severity:'error', summary:'Rejestracja drużyny', detail:'Proszę podać numer indeksu dla podanego zawodnika.'});
      return;
    }
    if(this.player.position == null || this.player.position == undefined) {
      this.messageService.add({life:8000, severity:'error', summary:'Rejestracja drużyny', detail:'Proszę wybrać pozycję dla podanego zawodnika.'});
      return;
    }
    let newPlayer = new StudentPlayer(this.player.indexNumber, this.player.position, this.player.isReferee);
    newPlayer.position = mapToEnumValue(newPlayer.position);
    newPlayer.isReferee == undefined ? newPlayer.isReferee = false : newPlayer.isReferee; // jesli nie zaznaczono checkboxa, to czasami isReferee leci jako undedfined, wiec zmieniam tutaj na false
    this.players.push(newPlayer);
  }

  removePlayer(player: StudentPlayer){
    this.players = this.players.filter(e => e != player);
  }

  ngOnInit(): void {
    this.positions = ['Napastnik', 'Pomocnik', 'Obrońca', 'Bramkarz']
  }

  btnDecline(): void {
    this.players.splice(0);
    this.router.navigateByUrl("/");
    this.messageService.add({life: 8000, severity:'info', summary:'Rejestracja drużyny', detail:'Anulowano rejestrację drużyny'});
  }

  btnConfirm() {
    this.team.players = this.players; // przypisanie studentow listy zawodników drużyny
    this.team.captainId = Number(localStorage.getItem('id')) || undefined;
    this.registerTeam();
  }

  public registerTeam() {
    this.studentService
      .registerTeam(this.team)
      .subscribe( (response: any) => {
        this.errorsListDto = response;
        if(!this.errorsListDto.listOfErrorsEmpty) {
          this.errorsListDto.errors.forEach((error) =>
            this.messageService.add({life:5000, severity:'error', summary:'Rejestracja drużyny', detail:error})
            );
        }
        else{
          this.messageService.add({life: 7000, severity:'success', summary:'Rejestracja drużyny', detail:'Gratulacje! Udało się zarejestrować drużynę.'});
          this.messageService.add({life: 7000, severity:'info', summary:'Informacja', closable:false , detail:'Tutaj znajdziesz informację o kolejnych krokach, które powinieneś podjąć.'});
          this.router.navigateByUrl("/contact");
        }
      })
  }

}
