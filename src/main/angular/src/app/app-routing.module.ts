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
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import { AuthenticationGuard } from "./authentication.guard";

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
      { path: 'transfer', component: TransferComponent },
      { path: 'table', component: TableComponent },
      { path: 'contact', component: ContactComponent },
      { path: 'schedule', component: ScheduleComponent },
      { path: 'users/update-to-student', component: UpdateToStudentComponent },
      { path: 'admin-accept-player', component: AdminAcceptPlayerComponent },
      { path: 'reservation-list', component: ReservationListComponent},
      { path: '**', redirectTo: '' }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
