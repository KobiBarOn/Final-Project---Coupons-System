import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-all-company-coupons',
  templateUrl: './all-company-coupons.component.html',
  styleUrls: ['./all-company-coupons.component.css'],
})
export class AllCompanyCouponsComponent implements OnInit {
  public allCompanyCoupons!: Coupon[];

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.companyService.getCompanyCoupons().subscribe(
      (p) => {
        setTimeout(() => {
          this.allCompanyCoupons = p;
        }, 2000);
      },
      (e) => {
        this.allCompanyCoupons = [];
        alert(e.error);
      }
    );
  }
}
