import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css'],
})
export class AddCustomerComponent implements OnInit {
  public customer = new Customer();

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {}

  public addCustomer() {
    this.adminService.addCustomer(this.customer).subscribe(
      (p) => {
        console.dir(p);
        this.customer = p;
        alert('Customer was added, ID: ' + p.id);
        this.router.navigate(['admin-menu/all-customers']);
      },
      (e) => {
        alert(e.error);
      }
    );
  }
}
