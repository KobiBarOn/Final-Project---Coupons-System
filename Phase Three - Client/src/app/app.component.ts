import { Component } from '@angular/core';
import { ClientType } from './models/client-type.enum';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-layout',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'CouponSystemWebsite';
  clientType?: ClientType;
  email = '';
  password = '';
  token?: string;
  resp?: string;

  constructor(private loginService: LoginService) {}
}
