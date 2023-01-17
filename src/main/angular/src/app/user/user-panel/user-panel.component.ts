import { Component, OnInit } from '@angular/core';
import {UserEditDTO} from "./user-edit-dto";
import {ErrorsListDTO} from "../../basic/error-list/error-list";
import {UserPanelService} from "./user-panel.service";
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {LoginService} from "../../login/login.service";

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.scss']
})
export class UserPanelComponent implements OnInit {

  user: UserEditDTO = new UserEditDTO();
  id: any;
  errorsListDto: ErrorsListDTO = new ErrorsListDTO();

  constructor(
    private userPanelService: UserPanelService,
    private router: Router,
    private messageService: MessageService,
    public loginService: LoginService
  ) {
  }

  ngOnInit(): void {
    this.id = this.loginService.getId();
    this.getUserById();
  }

  public getUserById(){
    this.id = this.loginService.getId();
    this.userPanelService.getUserEditDTOById().subscribe((response: any) => {
      this.user = response;
    })
  }

  public updateUser(): void {
    this.userPanelService.putUser(this.user).subscribe((response: any) => {
      this.errorsListDto = response;
      if( !this.errorsListDto.listOfErrorsEmpty ) {
        this.errorsListDto.errors.forEach((error) =>
          this.messageService.add({life:4000, severity:'error', summary:'Panel Użytkownika', detail:error})
        );
      }
      else{
        this.messageService.add({life: 4000, severity:'success', summary:'Panel Użytkownika', detail:'Pomyślnie edytowano dane!'});
        setTimeout(location.reload.bind(location), 1500);
      }
    });
  }

  public deleteUserById(): void {
    let userId: number = this.loginService.getId();
    this.userPanelService.deleteUserById(userId).subscribe((response: any) =>{
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty) {
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else{
        this.messageService.add({life: 8000, severity:'success', summary:'Usunięto', detail:'Pomyślnie usunięto konto!'});
        this.loginService.logout();
        this.router.navigateByUrl("/");
      }
    })
  }

  public btnDelete(){
    this.deleteUserById();
  }

  public btnUpdate() {
    this.updateUser();
  }
}
