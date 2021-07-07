import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientType } from 'src/app/models/client-type.enum';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  private token!: string;
  public email!: string;
  public password!: string;
  public clientType!: ClientType;

  constructor(private router: Router, private loginService: LoginService) {}

  ngOnInit(): void {}

  public login() {
    this.loginService
      .login(this.email, this.password, this.clientType)
      .subscribe(
        (resp) => {
          alert('You are logged in');
          console.dir(resp);
          if (this.clientType == 'ADMINISTRATOR') {
            this.token = resp.token!;
            localStorage.setItem('token', resp.token!);
            this.router.navigate(['/admin-menu']);
          } else if (this.clientType == 'COMPANY') {
            this.token = resp.token!;
            localStorage.setItem('token', resp.token!);
            this.router.navigate(['/company-menu']);
          } else if (this.clientType == 'CUSTOMER') {
            this.token = resp.token!;
            localStorage.setItem('token', resp.token!);
            this.router.navigate(['/customer-menu']);
          }
        },
        (e) => {
          console.dir(e);
          alert(e.error.message);
          this.router.navigate(['login']);
        }
      );
  }
}
