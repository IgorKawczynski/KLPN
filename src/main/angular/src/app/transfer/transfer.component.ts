import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from '../basic/error-list/error-list';
import { LoginService } from '../login/login.service';
import { SingleTransferDTO } from './single-transfer';
import { TransferService } from './transfer.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.scss', '../reservation/reservation.component.scss']
})
export class TransferComponent implements OnInit {

  singleTransferDTO: SingleTransferDTO = new SingleTransferDTO;
  errorsListDTO: ErrorsListDTO = new ErrorsListDTO;

  constructor(
    private transferService: TransferService,
    private messageService: MessageService,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
  }

  btnCancel(): void {
    history.back();
  }

  btnConfirm(): void {
    this.singleTransferDTO.captainId = this.loginService.getId();
    this.createSingleTransfer();
  }

  public createSingleTransfer() {
    this.transferService
    .singleTransfer(this.singleTransferDTO)
    .subscribe( (response: any) => {
      this.errorsListDTO = response;
      if(!this.errorsListDTO.listOfErrorsEmpty) {
        this.errorsListDTO.errors.forEach((error) => {
          this.messageService.add({life: 6000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else {
        this.messageService.add({life:5000, severity:'success', summary:'Udało się!', detail:'Zawodnik został dodany do twojej drużyny!'})
      }
    })
  }

}
