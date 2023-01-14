import {
  AfterContentChecked,
  AfterContentInit,
  AfterViewChecked,
  Component,
  OnInit
} from '@angular/core';
import {TeamEditService} from "./team-edit.service";
import {LoginService} from "../../login/login.service";
import {TeamDTO} from "../team-dto";
import {PlayerStatsDTO} from "../team-detail/PlayerStatsDTO";
import {Router} from "@angular/router";
import {ErrorsListDTO} from "../../basic/error-list/error-list";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-team-edit',
  templateUrl: './team-edit.component.html',
  styleUrls: ['./team-edit.component.scss']
})
export class TeamEditComponent implements OnInit {

  team: any;
  userId!: number;
  playerStats!: PlayerStatsDTO[];
  errorsListDTO!: ErrorsListDTO;

  constructor(
    private teamEditService: TeamEditService,
    public loginService: LoginService,
    private router: Router,
    public messageService: MessageService) { }

  ngOnInit(): void {
    this.getTeam();
  }

  public btnRoute() : void {
    this.router.navigateByUrl('/transfer');
  }

  public btnDelete(rowId: number) : void {
    this.deletePlayerFromTeam(rowId);
  }

  public getTeam(): void {
    this.userId = this.loginService.getId();
    this.teamEditService.getTeam(this.userId).subscribe((response: TeamDTO) => {
      this.team = response;
      this.playerStats = response.playerAndStatsDTOs;
    });
  }

  public deletePlayerFromTeam(rowId: number): void {
    this.userId = this.loginService.getId();
    if (this.userId == rowId) {
      this.messageService.add({
        life: 8000,
        severity: 'error',
        summary: 'Błąd',
        detail: "Nie możesz usunąć siebie z drużyny, zgłoś się w tym celu do administratora!"
      })
      // setTimeout(location.reload.bind(location), 1000);
    } else {
      this.teamEditService.deletePlayerFromTeam(rowId)
        .subscribe((response: any) => {
          this.errorsListDTO = response;
          if (!this.errorsListDTO.listOfErrorsEmpty) {
            this.errorsListDTO.errors.forEach((error) => {
              this.messageService.add({life: 8000, severity: 'error', summary: 'Błąd', detail: error})
            });
          } else {
            this.messageService.add({
              life: 8000,
              severity: 'success',
              summary: 'Usunięto',
              detail: 'Pomyślnie usunięto zawodnika z drużyny!'
            });
            setTimeout(location.reload.bind(location), 1500);
          }
        })
    }
  }
}
