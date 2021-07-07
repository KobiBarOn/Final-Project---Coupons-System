import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-all-customer-coupons',
  templateUrl: './all-customer-coupons.component.html',
  styleUrls: ['./all-customer-coupons.component.css'],
})
export class AllCustomerCouponsComponent implements OnInit {
  public allCouponsPurchases!: Coupon[];

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.customerService.getCustomerCoupons().subscribe(
      (p) => {
        setTimeout(() => {
          this.allCouponsPurchases = p;
        }, 2000);
      },
      (e) => {
        this.allCouponsPurchases = [];
        alert(e.error);
      }
    );
  }
}
