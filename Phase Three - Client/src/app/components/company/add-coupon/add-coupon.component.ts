import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category.enum';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css'],
})
export class AddCouponComponent implements OnInit {
  public coupon = new Coupon();

  constructor(private companyService: CompanyService, private router: Router) {}

  ngOnInit(): void {}

  public addCoupon() {
    this.companyService.addCoupon(this.coupon).subscribe(
      (p) => {
        console.dir(p);
        this.coupon = p;
        alert('Coupon was created successfully');
        this.router.navigate(['company-menu/all-company-coupons']);
      },
      (e) => {
        alert(e.error);
      }
    );
  }
}
