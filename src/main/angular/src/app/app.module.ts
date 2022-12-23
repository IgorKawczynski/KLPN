import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin/admin.component';
import { StudentComponent } from './student/student.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TableComponent } from './table/table.component';
import { ReservationComponent } from './reservation/reservation.component';
import { TransferComponent } from './transfer/transfer.component';
import { MatchComponent } from './match/match.component';
import { TeamComponent } from './team/team.component';
import { ButtonModule } from "primeng/button";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    AdminComponent,
    StudentComponent,
    LoginComponent,
    RegisterComponent,
    TableComponent,
    ReservationComponent,
    TransferComponent,
    MatchComponent,
    TeamComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
