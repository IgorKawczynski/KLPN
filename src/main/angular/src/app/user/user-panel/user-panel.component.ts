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
      console.log("USER :: ", this.user);
    })
  }

  public updateUser(): void {
    this.userPanelService.putUser(this.user).subscribe((response: any) => {
      console.log(this.user);
      this.errorsListDto = response;
      if( !this.errorsListDto.listOfErrorsEmpty ) {
        this.errorsListDto.errors.forEach((error) =>
          this.messageService.add({life:4000, severity:'error', summary:'Panel Użytkownika', detail:error})
        );
      }
      else{
        this.messageService.add({life: 4000, severity:'success', summary:'Panel Użytkownika', detail:'Udało ci się zmienić swoje konto!'});
        window.location.reload();
      }
    });
  }
  public btnUpdate() {
    console.log(this.user)
    this.updateUser();
  }
}
