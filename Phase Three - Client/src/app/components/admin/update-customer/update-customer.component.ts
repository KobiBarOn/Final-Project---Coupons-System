import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css'],
})
export class UpdateCustomerComponent implements OnInit {
  public customer = new Customer();
  public isClick: boolean = false;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  public updateCustomer() {
    this.isClick = true;
    this.adminService.updateCustomer(this.customer).subscribe(
      (p) => {
        setTimeout(() => {
          console.dir(p);
          this.customer = p;
          this.isClick = false;
          alert('Customer was updated successfully');
        }, 2000);
      },
      (e) => {
        this.isClick = false;
        alert(e.error);
      }
    );
  }
}
