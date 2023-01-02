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
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';
import {InputMaskModule} from 'primeng/inputmask';
import {InputTextModule} from 'primeng/inputtext';
import {FormsModule} from "@angular/forms";
import {AvatarModule} from 'primeng/avatar';
import {AvatarGroupModule} from 'primeng/avatargroup';
import { FooterComponent } from './footer/footer.component';

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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    MenubarModule,
    InputMaskModule,
    InputTextModule,
    FormsModule,
    AvatarModule,
    AvatarGroupModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
