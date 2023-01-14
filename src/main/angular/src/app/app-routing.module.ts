import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { UserComponent } from "./user/user.component";
import { RegisterComponent } from "./register/register.component";
import { AdminComponent } from "./admin-components/admin-home/admin.component";
import { TransferComponent } from "./transfer/transfer.component";
import { TableComponent } from "./table/table.component";
import { TeamComponent } from "./team/team.component";
import { StudentComponent } from "./student/student.component";
import { ReservationComponent } from "./reservation/reservation.component";
import { MatchComponent } from "./match/match.component";
import { ContactComponent } from "./contact/contact.component";
import { ScheduleComponent } from "./schedule/schedule.component";
import { UpdateToStudentComponent } from './user/update-to-student/update-to-student.component';
import { AdminAcceptPlayerComponent } from './admin-components/admin-accept-player/admin-accept-player.component';
import { AdminAcceptTeamComponent } from './admin-components/admin-accept-team/admin-accept-team.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import { AuthenticationGuard } from "./authentication.guard";
import { ReservationEditComponent } from './reservation-edit/reservation-edit.component';
import { ReservationHistoryComponent } from './reservation-history/reservation-history.component';
import { TeamDetailComponent } from './team/team-detail/team-detail.component';
import { TeamEditComponent } from "./team/team-edit/team-edit.component";
import { NotificationComponent } from "./admin-components/notification/notification.component";
import { TeamManageComponent } from "./team/team-manage/team-manage.component";
import { RefereeMatchesComponent } from './referee-matches/referee-matches.component';
import { TransferListComponent } from './transfer/transfer-list/transfer-list.component';
import { MatchEditComponent } from './match-edit/match-edit.component';


const routes: Routes = [
  { path: '', canActivate:[AuthenticationGuard], children: [
      { path: '', component: HomeComponent},
      { path: 'admin', component: AdminComponent },
      { path: 'login', component: LoginComponent },
      { path: 'users', component: UserComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'match', component: MatchComponent },
      { path: 'reservation', component: ReservationComponent },
      { path: 'student', component: StudentComponent },
      { path: 'team', component: TeamComponent },
      { path: 'team-edit/:id', component: TeamEditComponent },
      { path: 'transfer', component: TransferComponent },
      { path: 'transfer-list', component: TransferListComponent },
      { path: 'table', component: TableComponent },
      { path: 'contact', component: ContactComponent },
      { path: 'schedule', component: ScheduleComponent },
      { path: 'users/update-to-student', component: UpdateToStudentComponent },
      { path: 'admin-accept-player', component: AdminAcceptPlayerComponent },
      { path: 'admin-accept-team', component: AdminAcceptTeamComponent },
      { path: 'notification', component: NotificationComponent },
      { path: 'reservation-list', component: ReservationListComponent},
      { path: 'reservation-edit/:id', component: ReservationEditComponent},
      { path: 'reservation-history', component: ReservationHistoryComponent},
      { path: 'team-detail:teamId', component: TeamDetailComponent},
      { path: 'team-detail/:id', component: TeamDetailComponent},
      { path: 'team-detail/**', component: TeamDetailComponent},
      { path: 'team-manage', component: TeamManageComponent},
      { path: 'referee-matches', component: RefereeMatchesComponent},
      { path: 'match-edit/:id', component: MatchEditComponent},
      { path: '**', redirectTo: '' }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
