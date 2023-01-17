import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {MessageService, PrimeNGConfig} from "primeng/api";
import { ErrorsListDTO } from '../basic/error-list/error-list'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  model: any = {};
  sessionId: any = "";
  userId: any = "";
  errorsListDTO: ErrorsListDTO = new ErrorsListDTO();

  constructor(
    private router: Router,
    private primengConfig: PrimeNGConfig,
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  ngOnInit(): void { }

  btnLogin() {
    this.login();
  }

  login() {
    let url = '/api/user/login';
    this.http.post<any>(url, {
      email: this.model.email,
      password: this.model.password
    }).subscribe(
      res => {
        console.log(res);
        if (res.sessionId != null) {
          if(res.isAdmin == true) {
            this.router.navigateByUrl("/admin");
            this.messageService.add({life:3000, severity:'info', summary:'Login', detail:" Udało ci się zalogować jako administrator!"})
          }
          else {
            this.router.navigateByUrl("/");
            if(res.isStudent == true) {
              this.messageService.add({life:4000, severity:'success', summary:'Login', detail:" Udało ci się zalogować!"})
              this.messageService.add({life:4000, severity:'info', summary:'Login', detail:" Posiadasz konto o statusie studenta!"})
            }
            else {
              this.messageService.add({life:4000, severity:'success', summary:'Login', detail:" Udało ci się zalogować!"})
            }
          }
          // NIE ZMIENIAĆ TU NICZEGO, JAK MASZ PYTANIE PISZ : kluseczkidziadka96@onet.pl
          // TRZEBA TO ROZKMINIĆ
          this.sessionId = res.sessionId;
          localStorage.setItem('email', this.model.email)
          localStorage.setItem('id', res.id)
          localStorage.setItem('isAdmin', res.isAdmin)
          localStorage.setItem('isStudent', res.isStudent)
          localStorage.setItem('name', res.name)
          localStorage.setItem('role', res.role)
          sessionStorage.setItem('token', this.sessionId);
        }
        else {
          res.errorsListDTO.errors.forEach((error: any) =>
            this.messageService.add({life:4000, severity:'error', summary:'Login', detail: error})
          );
        }
      });
  }
}
