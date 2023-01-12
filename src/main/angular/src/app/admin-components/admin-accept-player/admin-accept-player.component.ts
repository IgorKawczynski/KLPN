import { Component, OnInit } from '@angular/core';
import { AdminAcceptPlayerService } from './admin-accept-player.service';
import { UserToAcceptDto } from './userToAcceptDto';
import { ErrorsListDTO } from 'src/app/basic/error-list/error-list';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-admin-accept-player',
  templateUrl: './admin-accept-player.component.html',
  styleUrls: ['./admin-accept-player.component.scss']
})
export class AdminAcceptPlayerComponent implements OnInit {

  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  public users: UserToAcceptDto[] = [];
  public selectedUsers: UserToAcceptDto[] = [];
  public selectedUsersId: number[] = [];

  constructor(
    private adminAcceptPlayerService:  AdminAcceptPlayerService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.getUsers();
  }

  public getUsers(): void {
    this.adminAcceptPlayerService.getUsersToAccept().subscribe((response: any) =>{
      this.users = response;
    });
  }

  public getSelectedUsersId (): void {
    for (let user of this.selectedUsers) {
      this.selectedUsersId.push(user.id);
    }
  }

  public updateUsers (): void {
    this.adminAcceptPlayerService.updateUsers(this.selectedUsersId).subscribe((response: any) => {
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else {
        this.messageService.add({life: 8000, severity:'success', summary:'Zaakceptowano', detail:"Status wybranych użytkowników został pomyślnie zmieniony na 'student'."})
      }
    });
  }

  public deleteUsers (): void {
    this.adminAcceptPlayerService.rejectUsers(this.selectedUsersId).subscribe((response: any) => {
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else {
        this.messageService.add({life: 8000, severity:'success', summary:'Odrzucono', detail:"Status wybranych użytkowników nie został zmieniony na 'student'."})
      }
    });
  }

  public acceptUsers(): void {
    this.getSelectedUsersId();
    if(this.selectedUsersId.length == 0){
      return;
    }
    this.updateUsers();
    setTimeout(location.reload.bind(location), 3000);
  }

  public rejectUsers(): void {
    this.getSelectedUsersId();
    if(this.selectedUsersId.length == 0){
      return;
    }
    this.deleteUsers();
    setTimeout(location.reload.bind(location), 3000);
  }

}
