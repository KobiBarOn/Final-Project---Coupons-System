import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css'],
})
export class DeleteCustomerComponent implements OnInit {
  public id!: number;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  public deleteCustomer() {
    if (
      confirm(
        'Just double checking with you... Delete Customer with ID: ' +
          this.id +
          ' ?'
      )
    ) {
      this.adminService.deleteCustomer(this.id).subscribe(
        (p) => {
          console.dir(p);
          this.id = p;
          alert('Customer with ID: ' + p + ', was deleted');
        },
        (e) => {
          alert(e.error);
        }
      );
    } else {
      alert('Customer Deletion is Cancelled');
    }
  }
}
