import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-all-company-coupons-by-max-price',
  templateUrl: './all-company-coupons-by-max-price.component.html',
  styleUrls: ['./all-company-coupons-by-max-price.component.css'],
})
export class AllCompanyCouponsByMaxPriceComponent implements OnInit {
  public allCompanyCouponsByMaxPrice!: Coupon[];
  public maxPrice!: number;
  public isClick: boolean = false;

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  public getCompanyCouponsByMaxPrice() {
    this.isClick = true;
    this.companyService.getCompanyCouponsByMaxPrice(this.maxPrice).subscribe(
      (p) => {
        setTimeout(() => {
          this.allCompanyCouponsByMaxPrice = p;
          this.isClick = false;
        }, 2000);
      },
      (e) => {
        this.allCompanyCouponsByMaxPrice = [];
        this.isClick = false;
        alert(e.error);
      }
    );
  }
}
