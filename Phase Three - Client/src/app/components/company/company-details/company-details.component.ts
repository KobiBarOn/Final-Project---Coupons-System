import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-company-details',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.css'],
})
export class CompanyDetailsComponent implements OnInit {
  public company = new Company();

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.companyService.getCompanyDetails().subscribe(
      (p) => {
        this.company = p;
      },
      (e) => {
        alert(e.error);
      }
    );
  }
}
