import { Component, OnInit } from '@angular/core';
import {TeamsToAcceptDto} from "./teamsToAcceptDto";
import { AdminAcceptTeamService } from './admin-accept-team.service';
import { ErrorsListDTO } from 'src/app/basic/error-list/error-list';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-admin-accept-team',
  templateUrl: './admin-accept-team.component.html',
  styleUrls: ['./admin-accept-team.component.scss']
})
export class AdminAcceptTeamComponent implements OnInit {

  errorsListDto: ErrorsListDTO = new ErrorsListDTO;

  public teams: TeamsToAcceptDto[] = [];

  public selectedTeams: TeamsToAcceptDto[] = [];

  public selectedTeamsId :number[] = []
  constructor(private adminAcceptTeamService: AdminAcceptTeamService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.getTeams();
  }


  public getTeams(): void {
    this.adminAcceptTeamService.getTeamsToAccept().subscribe((response: any) =>{
      this.teams = response;
    });
  }

  public getSelectedTeamsId (): void {
    for (let team of this.selectedTeams) {
      this.selectedTeamsId.push(team.id);
    }
  }

  public updateTeams(): void {
    this.adminAcceptTeamService.updateTeams(this.selectedTeamsId).subscribe((response: any) =>{
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty){
        this.errorsListDto.errors.forEach((error) =>{
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else {
        this.messageService.add({life: 8000, severity:'success', summary:'Zaakceptowano',
          detail:"Wybrane drużyny zostały zaakceptowane."})
      }
    });
  }

  public rejectTeams(): void {
    this.adminAcceptTeamService.rejectTeams(this.selectedTeamsId).subscribe((response: any) =>{
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty){
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 8000, severity:'success', summary:'Odrzucono',
          detail:"Wybrane drużyny zostały odrzucone."})
      }
    });
}
  public acceptTeams(): void {
    this.getSelectedTeamsId();
    if(this.selectedTeamsId.length == 0){
      return;
    }
    this.updateTeams();
    setTimeout(location.reload.bind(location), 3000);
  }

  public deleteTeam(): void {
    this.getSelectedTeamsId();
    if(this.selectedTeamsId.length == 0){
      return;
    }
    this.rejectTeams();
    setTimeout(location.reload.bind(location), 3000);
  }

}
