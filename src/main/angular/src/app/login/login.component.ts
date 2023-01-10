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
          this.sessionId = res.sessionId;
          sessionStorage.setItem('token', this.sessionId);
          this.router.navigate(['localhost:4200/']);
          this.messageService.add({life:4000, severity:'success', summary:'Login', detail:" Udało ci się zalogować !"})
          localStorage.setItem('email', this.model.email)
        }
        else {
          res.errorsListDTO.errors.forEach((error: any) =>
            this.messageService.add({life:4000, severity:'error', summary:'Login', detail: error})
          );
        }
      });

  }
  history() {

  }


}
