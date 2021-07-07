import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../models/company.model';
import { Customer } from '../models/customer.model';
import { LoginItem } from '../models/login-item.model';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private httpClient: HttpClient) {}

  public addCompany(company: Company): Observable<Company> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.post<Company>(
      'http://localhost:8080/api/admin/company/add',
      company,
      options
    );
  }

  public updateCompany(company: Company): Observable<Company> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.put<Company>(
      'http://localhost:8080/api/admin/company/update',
      company,
      options
    );
  }

  public deleteCompany(id: number): Observable<number> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.delete<number>(
      'http://localhost:8080/api/admin/company/delete?id=' + id,
      options
    );
  }

  public getAllCompanies(): Observable<Company[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Company[]>(
      'http://localhost:8080/api/admin/company/all-companies',
      options
    );
  }

  public getOneCompany(id: number): Observable<Company> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Company>(
      'http://localhost:8080/api/admin/company/single-company?id=' + id,
      options
    );
  }

  public addCustomer(customer: Customer): Observable<Customer> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.post<Customer>(
      'http://localhost:8080/api/admin/customer/add',
      customer,
      options
    );
  }

  public updateCustomer(customer: Customer): Observable<Customer> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.put<Customer>(
      'http://localhost:8080/api/admin/customer/update',
      customer,
      options
    );
  }

  public deleteCustomer(id: number): Observable<number> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.delete<number>(
      'http://localhost:8080/api/admin/customer/delete?id=' + id,
      options
    );
  }

  public getAllCustomers(): Observable<Customer[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Customer[]>(
      'http://localhost:8080/api/admin/customer/all-customers',
      options
    );
  }

  public getOneCustomer(id: number): Observable<Customer> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Customer>(
      'http://localhost:8080/api/admin/customer/single-customer?id=' + id,
      options
    );
  }
}
