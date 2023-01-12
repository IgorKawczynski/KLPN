import { Component, OnInit } from '@angular/core';
import {UserRequestDTO} from "../user/user-request";
import {ErrorsListDTO} from "../basic/error-list/error-list";
import {MessageService, PrimeNGConfig} from "primeng/api";
import {RegisterService} from "./register.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  userRequestDto: UserRequestDTO = new UserRequestDTO();
  errorsListDto: ErrorsListDTO = new ErrorsListDTO();

  constructor(
    private registerService: RegisterService,
    private primengConfig: PrimeNGConfig,
    private router: Router,
    private messageService: MessageService,
  ){
  }

  ngOnInit(): void {
  }

  btnRegister(): void {
    console.log(this.userRequestDto);
    this.registerUser();
  }

  btnRoute(url: string) {
    this.router.navigateByUrl(url);
  }

  public registerUser() {
    this.registerService
      .register(this.userRequestDto)
      .subscribe( (response: any) => {
        this.errorsListDto = response;
        console.log(this.errorsListDto);
        if(!this.errorsListDto.listOfErrorsEmpty) {
          this.errorsListDto.errors.forEach((error) =>
            this.messageService.add({life:3000, severity:'error', summary:'Register', detail:error})
          );
        }
        else{
          this.messageService.add({life: 3000, severity:'success', summary:'Register', detail:'You have successfully signed up!'});
          this.messageService.add({life: 5500, severity:'info', summary:'Register', detail:'Now you can log in with your credentials!'});
          this.router.navigateByUrl('/login');
        }
      });
  }

}
