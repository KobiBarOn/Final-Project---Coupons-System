import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginItem } from 'src/app/models/login-item.model';
import { LogoutService } from 'src/app/services/logout.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
})
export class LogoutComponent implements OnInit {
  private loginItem = new LoginItem();

  constructor(private logoutService: LogoutService, private router: Router) {}

  ngOnInit(): void {
    this.loginItem.token = localStorage.getItem('token')!;
    console.dir(this.loginItem.token);
    if (this.loginItem.token != null) {
      this.logoutService.logout().subscribe(
        (p) => {
          this.loginItem.token = p;
          localStorage.clear();
          this.router.navigate(['home']);
          alert('Logout success');
        },
        (e) => {
          console.dir(e);
          alert(e.error);
        }
      );
    } else {
      alert('You are not Logged-in');
      this.router.navigate(['login']);
    }
  }
}
