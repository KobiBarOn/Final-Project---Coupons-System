import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-update-coupon',
  templateUrl: './update-coupon.component.html',
  styleUrls: ['./update-coupon.component.css'],
})
export class UpdateCouponComponent implements OnInit {
  public coupon = new Coupon();
  public company!: any;
  public isClick: boolean = false;

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  public updateCoupon() {
    this.isClick = true;
    this.companyService.updateCompany(this.coupon).subscribe(
      (p) => {
        setTimeout(() => {
          console.dir(p);
          this.coupon = p;
          this.company = p.company;
          this.isClick = false;
          alert('Coupon was updated successfully');
        }, 2000);
      },
      (e) => {
        this.isClick = false;
        alert(e.error);
      }
    );
  }
}
