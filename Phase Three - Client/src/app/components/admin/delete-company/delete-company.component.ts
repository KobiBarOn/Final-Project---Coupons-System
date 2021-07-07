import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-delete-company',
  templateUrl: './delete-company.component.html',
  styleUrls: ['./delete-company.component.css'],
})
export class DeleteCompanyComponent implements OnInit {
  public id!: number;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  public deleteCompany() {
    if (
      confirm(
        'Just double checking with you... Delete Company with ID: ' +
          this.id +
          ' ?'
      )
    ) {
      this.adminService.deleteCompany(this.id).subscribe(
        (p) => {
          console.dir(p);
          this.id = p;
          alert('Company with ID: ' + p + ', was deleted');
        },
        (e) => {
          alert(e.error);
        }
      );
    } else {
      alert('Company Deletion is Cancelled');
    }
  }
}
