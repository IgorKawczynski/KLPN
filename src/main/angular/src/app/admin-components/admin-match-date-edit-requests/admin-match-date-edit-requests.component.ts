import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ErrorsListDTO } from 'src/app/basic/error-list/error-list';
import { AdminMatchDateEditRequestsService } from './admin-match-date-edit-requests.service';
import { MatchDateEditResponseDTO } from './match-date-edit-response';

@Component({
  selector: 'app-admin-match-date-edit-requests',
  templateUrl: './admin-match-date-edit-requests.component.html',
  styleUrls: ['./admin-match-date-edit-requests.component.scss', 
  '../admin-accept-player/admin-accept-player.component.scss']
})
export class AdminMatchDateEditRequestsComponent implements OnInit {

  public matchDateRequests: MatchDateEditResponseDTO[] = [];
  public selectedRequests: MatchDateEditResponseDTO[] = [];
  public selectedRequestsId: number[] = [];
  public errorsListDTO: ErrorsListDTO = new ErrorsListDTO();
  
  constructor(
    private matchDateEditService: AdminMatchDateEditRequestsService,
    private messageService: MessageService,
  ) { }

  ngOnInit(): void {
    this.getRequests();
  }

  public getRequests(): void {
    this.matchDateEditService.getMatchDateEditRequests().subscribe((response: any) => {
      this.matchDateRequests = response;
    });
  }

  public getSelectedRequestsId(): void {
    for(let request of this.selectedRequests) {
      this.selectedRequestsId.push(request.matchDateEditId);
    }
  }

  public btnAccept(): void {
    this.getSelectedRequestsId();
    if(this.selectedRequestsId.length == 0){
      return;
    }
    this.acceptRequests();
    this.clearSelectedRequestsId();
  }

  public btnReject(): void {
    this.getSelectedRequestsId();
    if(this.selectedRequestsId.length == 0){
      return;
    }
    this.rejectRequests();
    this.clearSelectedRequestsId();

  }

  public clearSelectedRequestsId(): void {
    this.selectedRequestsId = [];
  }

  public acceptRequests(): void {
    this.matchDateEditService
    .acceptMatchDateRequests(this.selectedRequestsId).subscribe((response: any) => {
      this.errorsListDTO = response;
      if(!this.errorsListDTO.listOfErrorsEmpty) {
        this.errorsListDTO.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else {
        this.messageService.add({life: 8000, severity:'success', summary:'Zaakceptowano', detail:"Daty spotkań zostały zmienione."})
        setTimeout(location.reload.bind(location), 1500);
      }
    });
  }

  public rejectRequests(): void {
    this.matchDateEditService
    .rejectMatchDateRequests(this.selectedRequestsId).subscribe((response: any) => {
      this.errorsListDTO = response;
      if(!this.errorsListDTO.listOfErrorsEmpty) {
        this.errorsListDTO.errors.forEach((error) => {
          this.messageService.add({life: 8000, severity:'error', summary:'Błąd', detail:error})
        });
      }
      else {
        this.messageService.add({life: 8000, severity:'success', summary:'Zaakceptowano', detail:"Wybrane wnioski zostały odrzucone."})
        setTimeout(location.reload.bind(location), 1500);
      }
    });
  }

}
