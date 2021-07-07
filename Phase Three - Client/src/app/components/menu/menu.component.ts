import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent implements OnInit {
  private token!: string;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {}

  public isClientLoggedIn() {
    this.token = localStorage.getItem('token')!;
    if (this.token != null) {
      return true;
    } else {
      return false;
    }
  }
}
