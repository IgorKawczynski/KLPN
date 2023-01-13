import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from 'src/app/basic/error-list/error-list';
import { UserService } from '../user.service';
import { UserUpdateToStudentDto } from './update-to-student';
import { LoginService } from '../../login/login.service';
@Component({
  selector: 'app-update-to-student',
  templateUrl: './update-to-student.component.html',
  styleUrls: ['./update-to-student.component.scss', '../../reservation/reservation.component.scss']
})
export class UpdateToStudentComponent implements OnInit {

  errorsListDto: ErrorsListDTO = new ErrorsListDTO;
  updateToStudentDto: UserUpdateToStudentDto = new UserUpdateToStudentDto;

  constructor(
    private userService: UserService,
    private router: Router,

    private loginservice: LoginService,
    private messageService: MessageService) { }

  ngOnInit(): void {
  }

  btnCancel(): void {
    history.back();
  }

  btnConfirm(): void {
    console.log(this.updateToStudent);
    this.updateToStudentDto.userId = this.loginservice.getId();
    this.updateToStudent();
  }

  public updateToStudent() {
    this.userService
    .updateToStudent(this.updateToStudentDto)
    .subscribe( (response: any) => {
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty){
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 6000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else {
        this.router.navigateByUrl('/');
        this.messageService.add({life: 5000, severity:'success', summary:'Zaakceptowane', detail:"Twój wniosek został zaakceptowany i czeka na akceptacje przez administratora."})
      }
    })
  }




}
