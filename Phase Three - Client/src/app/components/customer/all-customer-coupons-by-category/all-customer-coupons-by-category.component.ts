import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category.enum';
import { Coupon } from 'src/app/models/coupon.model';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-all-customer-coupons-by-category',
  templateUrl: './all-customer-coupons-by-category.component.html',
  styleUrls: ['./all-customer-coupons-by-category.component.css'],
})
export class AllCustomerCouponsByCategoryComponent implements OnInit {
  public allCustomerCouponsByCategory!: Coupon[];
  public category!: Category;
  public isClick: boolean = false;

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {}

  public getCustomerCouponsByCategory() {
    this.isClick = true;
    this.customerService.getCustomerCouponsByCategory(this.category).subscribe(
      (p) => {
        setTimeout(() => {
          this.allCustomerCouponsByCategory = p;
          this.isClick = false;
        }, 2000);
      },
      (e) => {
        this.allCustomerCouponsByCategory = [];
        this.isClick = false;
        alert(e.error);
      }
    );
  }
}
