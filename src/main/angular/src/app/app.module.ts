import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { AdminComponent } from './admin-components/admin-home/admin.component';
import { StudentComponent } from './student/student.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TableComponent } from './table/table.component';
import { ReservationComponent } from './reservation/reservation.component';
import { TransferComponent } from './transfer/transfer.component';
import { MatchComponent } from './match/match.component';
import { TeamComponent } from './team/team.component';
import { ButtonModule } from "primeng/button";
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';
import { InputMaskModule } from 'primeng/inputmask';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from "@angular/forms";
import { AvatarModule } from 'primeng/avatar';
import { AvatarGroupModule } from 'primeng/avatargroup';
import { FooterComponent } from './footer/footer.component';
import { PasswordModule } from 'primeng/password';
import { CalendarModule } from 'primeng/calendar';
import { ContactComponent } from './contact/contact.component';
import { GMapModule } from 'primeng/gmap';
import { DividerModule } from 'primeng/divider';
import { DropdownModule } from 'primeng/dropdown';
import { DialogModule } from 'primeng/dialog';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { KeyFilterModule } from 'primeng/keyfilter';
import { InputNumberModule } from 'primeng/inputnumber';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { RequestInterceptor } from "./request.interceptor";
import {AutoCompleteModule} from 'primeng/autocomplete';
import {CascadeSelectModule} from 'primeng/cascadeselect';
import {CheckboxModule} from 'primeng/checkbox';
import { UpdateToStudentComponent } from './user/update-to-student/update-to-student.component';
import { AdminNavbarComponent } from './admin-components/admin-navbar/admin-navbar.component';
import { AdminFooterComponent } from './admin-components/admin-footer/admin-footer.component';
import {ScheduleComponent} from "./schedule/schedule.component";
import {TreeTableModule} from 'primeng/treetable';
import { AdminAcceptPlayerComponent } from './admin-components/admin-accept-player/admin-accept-player.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';
import {RippleModule} from "primeng/ripple";
import { ReservationEditComponent } from './reservation-edit/reservation-edit.component';
import { ReservationHistoryComponent } from './reservation-history/reservation-history.component';
import { AdminAcceptTeamComponent } from './admin-components/admin-accept-team/admin-accept-team.component';
import { TeamDetailComponent } from './team/team-detail/team-detail.component';
import { TeamEditComponent } from './team/team-edit/team-edit.component';
import { NotificationComponent } from './admin-components/notification/notification.component';
import { TeamManageComponent } from './team/team-manage/team-manage.component';
import { RefereeMatchesComponent } from './referee-matches/referee-matches.component';
import { TransferListComponent } from './transfer/transfer-list/transfer-list.component';
import { StudentMatchesComponent } from './student-matches/student-matches.component';
import { MatchEditComponent } from './match-edit/match-edit.component';
import { MatchDateEditComponent } from './match-date-edit/match-date-edit.component';
import { AdminMatchDateEditRequestsComponent } from './admin-components/admin-match-date-edit-requests/admin-match-date-edit-requests.component';
import { UserPanelComponent } from './user/user-panel/user-panel.component';

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
    TeamComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    ContactComponent,
    AdminNavbarComponent,
    AdminFooterComponent,
    UpdateToStudentComponent,
    ScheduleComponent,
    AdminAcceptPlayerComponent,
    ReservationListComponent,
    ReservationEditComponent,
    ReservationHistoryComponent,
    AdminAcceptTeamComponent,
    TeamEditComponent,
    NotificationComponent,
    TeamDetailComponent,
    TeamManageComponent,
    RefereeMatchesComponent,
    TeamManageComponent,
    TransferListComponent,
    MatchEditComponent,
    StudentMatchesComponent,
    MatchDateEditComponent,
    AdminMatchDateEditRequestsComponent,
    UserPanelComponent
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        ButtonModule,
        MenubarModule,
        InputMaskModule,
        InputTextModule,
        FormsModule,
        AvatarModule,
        AvatarGroupModule,
        PasswordModule,
        CalendarModule,
        GMapModule,
        DividerModule,
        BrowserAnimationsModule,
        TableModule,
        InputNumberModule,
        KeyFilterModule,
        ToastModule,
        DialogModule,
        DropdownModule,
        AutoCompleteModule,
        CascadeSelectModule,
        CheckboxModule,
        TreeTableModule,
        RippleModule
    ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true }, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
