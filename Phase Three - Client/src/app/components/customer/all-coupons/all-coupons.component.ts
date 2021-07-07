import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Coupon } from 'src/app/models/coupon.model';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-all-coupons',
  templateUrl: './all-coupons.component.html',
  styleUrls: ['./all-coupons.component.css'],
})
export class AllCouponsComponent implements OnInit {
  public allCoupons!: Coupon[];
  public coupon = new Coupon();

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.customerService.getAllCoupons().subscribe(
      (p) => {
        setTimeout(() => {
          this.allCoupons = p;
        }, 2000);
      },
      (e) => {
        this.allCoupons = [];
        alert(e.error);
      }
    );
  }

  public purchaseCoupon(coupon: Coupon) {
    if (confirm('Confirm Purchase?')) {
      this.customerService.purchaseCoupon(coupon.id!).subscribe(
        (p) => {
          setTimeout(() => {
            this.coupon = p;
            alert(
              'Coupon purchased Successfully. View receipt in your Purchase History page...'
            );
            this.router.navigate(['/customer-menu/all-customer-coupons']);
          }, 2000);
        },
        (e) => {
          alert(e.error);
          this.router.navigate(['/customer-menu/all-customer-coupons']);
        }
      );
    } else {
      alert('Coupon Purchase is Cancelled');
    }
  }
}
