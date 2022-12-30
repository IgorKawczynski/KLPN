import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { UserComponent } from "./user/user.component";
import { RegisterComponent } from "./register/register.component";
import { AdminComponent } from "./admin/admin.component";
import { TransferComponent } from "./transfer/transfer.component";
import { TableComponent } from "./table/table.component";
import { TeamComponent } from "./team/team.component";
import { StudentComponent } from "./student/student.component";
import { ReservationComponent } from "./reservation/reservation.component";
import { MatchComponent } from "./match/match.component";

const routes: Routes = [
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
      { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
