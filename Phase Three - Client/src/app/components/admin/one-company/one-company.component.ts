import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-one-company',
  templateUrl: './one-company.component.html',
  styleUrls: ['./one-company.component.css'],
})
export class OneCompanyComponent implements OnInit {
  public id!: number;
  public company = new Company();
  public isClick: boolean = false;

  constructor(private adminSevice: AdminService) {}

  ngOnInit(): void {}

  public getOneCompany() {
    this.isClick = true;
    this.adminSevice.getOneCompany(this.id).subscribe(
      (p) => {
        setTimeout(() => {
          this.company = p;
          this.isClick = false;
        }, 2000);
      },
      (e) => {
        this.isClick = false;
        alert(e.error);
      }
    );
  }
}
