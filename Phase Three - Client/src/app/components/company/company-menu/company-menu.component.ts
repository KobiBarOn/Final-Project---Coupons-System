import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-company-menu',
  templateUrl: './company-menu.component.html',
  styleUrls: ['./company-menu.component.css'],
})
export class CompanyMenuComponent implements OnInit {
  constructor(public activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {}
}
