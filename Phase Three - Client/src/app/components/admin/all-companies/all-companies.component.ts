import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-all-companies',
  templateUrl: './all-companies.component.html',
  styleUrls: ['./all-companies.component.css'],
})
export class AllCompaniesComponent implements OnInit {
  public allCompanies!: Company[];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.adminService.getAllCompanies().subscribe(
      (p) => {
        setTimeout(() => {
          this.allCompanies = p;
        }, 2000);
      },
      (e) => {
        this.allCompanies = [];
        alert(e.error);
      }
    );
  }
}
