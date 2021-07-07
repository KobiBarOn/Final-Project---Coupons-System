import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category.enum';
import { Coupon } from '../models/coupon.model';
import { Customer } from '../models/customer.model';
import { LoginItem } from '../models/login-item.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private httpClient: HttpClient) {}

  public getAllCoupons(): Observable<Coupon[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon[]>(
      'http://localhost:8080/api/customers/coupon/all-coupons',
      options
    );
  }

  public purchaseCoupon(id: number): Observable<Coupon> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon>(
      'http://localhost:8080/api/customers/coupon/purchase?id=' + id,
      options
    );
  }

  public getCustomerCoupons(): Observable<Coupon[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon[]>(
      'http://localhost:8080/api/customers/customer/all-coupon-purchases',
      options
    );
  }

  public getOneCoupon(id: number): Observable<Coupon> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon>(
      'http://localhost:8080/api/customers/coupon/single-coupon?id=' + id,
      options
    );
  }

  public getCustomerCouponsByCategory(
    category: Category
  ): Observable<Coupon[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon[]>(
      'http://localhost:8080/api/customers/customer/all-coupon-purchases-by-category?category=' +
        category,
      options
    );
  }

  public getCustomerCouponsByMaxPrice(maxPrice: number): Observable<Coupon[]> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Coupon[]>(
      'http://localhost:8080/api/customers/customer/all-coupon-purchases-by-MAX-price?maxPrice=' +
        maxPrice,
      options
    );
  }

  public getCustomerDetails(): Observable<Customer> {
    let loginItem = new LoginItem();
    loginItem.token = localStorage.getItem('token')!;
    let theHeaders = new HttpHeaders().set('token', loginItem.token);
    let options = { headers: theHeaders };

    return this.httpClient.get<Customer>(
      'http://localhost:8080/api/customers/logged-in-customer/details',
      options
    );
  }
}
