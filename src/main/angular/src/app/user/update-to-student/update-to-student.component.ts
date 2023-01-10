import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from 'src/app/basic/error-list/error-list';
import { UserService } from '../user.service';
import { UserUpdateToStudentDto } from './update-to-student';

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
    private messageService: MessageService) { }

  ngOnInit(): void {
  }

  btnConfirm(): void {
    console.log(this.updateToStudent);
    this.updateToStudentDto.userId = 1040; // do czasu az nie bedzie pobieralo id z sesji
    this.updateToStudent();
  }

  public updateToStudent() {
    this.userService
    .updateToStudent(this.updateToStudentDto)
    .subscribe( (response: any) => {
      this.errorsListDto = response;
      if(!this.errorsListDto.listOfErrorsEmpty){
        this.errorsListDto.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else {
        this.messageService.add({life: 8000, severity:'success', summary:'Zaakceptowane', detail:"Twój wniosek został zaakceptowany i czeka na akceptacje przez administratora."})
      }
    })
  }


  

}
