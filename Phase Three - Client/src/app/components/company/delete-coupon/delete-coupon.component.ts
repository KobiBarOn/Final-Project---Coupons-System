import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-delete-coupon',
  templateUrl: './delete-coupon.component.html',
  styleUrls: ['./delete-coupon.component.css'],
})
export class DeleteCouponComponent implements OnInit {
  public id!: number;

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  public deleteCoupon() {
    if (
      confirm(
        'Just double checking with you... Delete Coupon with ID: ' +
          this.id +
          ' ?'
      )
    ) {
      this.companyService.deleteCoupon(this.id).subscribe(
        (p) => {
          console.dir(p);
          this.id = p;
          alert('Coupon with ID: ' + p + ', was deleted');
        },
        (e) => {
          alert(e.error);
        }
      );
    } else {
      alert('Coupon Deletion is Cancelled');
    }
  }
}
