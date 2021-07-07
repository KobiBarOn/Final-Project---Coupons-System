import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-one-company-coupon',
  templateUrl: './one-company-coupon.component.html',
  styleUrls: ['./one-company-coupon.component.css'],
})
export class OneCompanyCouponComponent implements OnInit {
  public id!: number;
  public coupon = new Coupon();
  public isClick: boolean = false;

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  public getOneCoupon() {
    this.isClick = true;
    this.companyService.getOneCoupon(this.id).subscribe(
      (p) => {
        setTimeout(() => {
          this.coupon = p;
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
