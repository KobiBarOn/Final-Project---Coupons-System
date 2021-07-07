import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css'],
})
export class UpdateCompanyComponent implements OnInit {
  public company = new Company();
  public isClick: boolean = false;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  public updateCompany() {
    this.isClick = true;
    this.adminService.updateCompany(this.company).subscribe(
      (p) => {
        setTimeout(() => {
          console.dir(p);
          this.company = p;
          this.isClick = false;
          alert('Company was updated successfully');
        }, 2000);
      },
      (e) => {
        this.isClick = false;
        alert(e.error);
      }
    );
  }
}
