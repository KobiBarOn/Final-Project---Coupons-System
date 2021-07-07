import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css'],
})
export class AddCompanyComponent implements OnInit {
  public company = new Company();

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {}

  public addCompany() {
    this.adminService.addCompany(this.company).subscribe(
      (p) => {
        console.dir(p);
        this.company = p;
        alert('Company was added, ID: ' + p.id);
        this.router.navigate(['admin-menu/all-companies']);
      },
      (e) => {
        alert(e.error);
      }
    );
  }
}
