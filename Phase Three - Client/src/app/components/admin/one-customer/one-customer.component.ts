import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-one-customer',
  templateUrl: './one-customer.component.html',
  styleUrls: ['./one-customer.component.css'],
})
export class OneCustomerComponent implements OnInit {
  public id!: number;
  public customer = new Customer();
  public isClick: boolean = false;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  public getOneCustomer() {
    this.isClick = true;
    this.adminService.getOneCustomer(this.id).subscribe(
      (p) => {
        setTimeout(() => {
          this.customer = p;
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
