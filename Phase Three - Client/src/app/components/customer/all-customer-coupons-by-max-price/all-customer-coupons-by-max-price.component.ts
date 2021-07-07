import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-all-customer-coupons-by-max-price',
  templateUrl: './all-customer-coupons-by-max-price.component.html',
  styleUrls: ['./all-customer-coupons-by-max-price.component.css'],
})
export class AllCustomerCouponsByMaxPriceComponent implements OnInit {
  public allCustomerCouponsByMaxPrice!: Coupon[];
  public maxPrice!: number;
  public isClick: boolean = false;

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {}

  public getCustomerCouponsByMaxPrice() {
    this.isClick = true;
    this.customerService.getCustomerCouponsByMaxPrice(this.maxPrice).subscribe(
      (p) => {
        setTimeout(() => {
          this.allCustomerCouponsByMaxPrice = p;
          this.isClick = false;
        }, 2000);
      },
      (e) => {
        this.allCustomerCouponsByMaxPrice = [];
        this.isClick = false;
        alert(e.error);
      }
    );
  }
}
