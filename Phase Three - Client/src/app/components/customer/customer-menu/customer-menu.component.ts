import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-customer-menu',
  templateUrl: './customer-menu.component.html',
  styleUrls: ['./customer-menu.component.css'],
})
export class CustomerMenuComponent implements OnInit {
  constructor(public activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {}
}
