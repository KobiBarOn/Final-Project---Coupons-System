import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category.enum';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-all-company-coupons-by-category',
  templateUrl: './all-company-coupons-by-category.component.html',
  styleUrls: ['./all-company-coupons-by-category.component.css'],
})
export class AllCompanyCouponsByCategoryComponent implements OnInit {
  public allCompanyCouponsByCategory!: Coupon[];
  public category!: Category;
  public isClick: boolean = false;

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  public getCompanyCouponsByCategory() {
    this.isClick = true;
    this.companyService.getCompanyCouponsByCategory(this.category).subscribe(
      (p) => {
        setTimeout(() => {
          this.allCompanyCouponsByCategory = p;
          this.isClick = false;
        }, 2000);
      },
      (e) => {
        this.allCompanyCouponsByCategory = [];
        this.isClick = false;
        alert(e.error);
      }
    );
  }
}
