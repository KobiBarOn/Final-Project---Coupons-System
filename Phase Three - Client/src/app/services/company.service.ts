import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category.enum';
import { Company } from '../models/company.model';
import { Coupon } from '../models/coupon.model';
import { LoginItem } from '../models/login-item.model';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  constructor(private httpClient: HttpClient) {}

  public addCoupon(coupon: Coupon): Observable<Coupon> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.post<Coupon>(
      'http://localhost:8080/api/companies/coupon/add',
      coupon,
      options
    );
  }

  public updateCompany(coupon: Coupon): Observable<Coupon> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.put<Coupon>(
      'http://localhost:8080/api/companies/coupon/update',
      coupon,
      options
    );
  }

  public deleteCoupon(id: number): Observable<number> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.delete<number>(
      'http://localhost:8080/api/companies/coupon/delete?id=' + id,
      options
    );
  }

  public getCompanyCoupons(): Observable<Coupon[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon[]>(
      'http://localhost:8080/api/companies/company/all-coupons',
      options
    );
  }

  public getOneCoupon(id: number): Observable<Coupon> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon>(
      'http://localhost:8080/api/companies/company/single-coupon?id=' + id,
      options
    );
  }

  public getCompanyCouponsByCategory(category: Category): Observable<Coupon[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon[]>(
      'http://localhost:8080/api/companies/company/all-coupons-by-category?category=' +
        category,
      options
    );
  }

  public getCompanyCouponsByMaxPrice(maxPrice: number): Observable<Coupon[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon[]>(
      'http://localhost:8080/api/companies/company/all-coupons-by-MAX-price?maxPrice=' +
        maxPrice,
      options
    );
  }

  public getCompanyDetails(): Observable<Company> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Company>(
      'http://localhost:8080/api/companies/logged-in-company/details',
      options
    );
  }
}
