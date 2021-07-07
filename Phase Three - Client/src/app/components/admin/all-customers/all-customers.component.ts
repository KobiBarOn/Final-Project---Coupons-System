import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-all-customers',
  templateUrl: './all-customers.component.html',
  styleUrls: ['./all-customers.component.css'],
})
export class AllCustomersComponent implements OnInit {
  public allCustomers!: Customer[];

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.adminService.getAllCustomers().subscribe(
      (p) => {
        setTimeout(() => {
          this.allCustomers = p;
        }, 2000);
      },
      (e) => {
        this.allCustomers = [];
        alert(e.error);
      }
    );
  }
}
