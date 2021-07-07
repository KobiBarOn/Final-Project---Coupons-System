import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './components/layout/layout.component';
import { FooterComponent } from './components/footer/footer.component';
import { MenuComponent } from './components/menu/menu.component';
import { HomeComponent } from './components/home/home.component';
import { Page404Component } from './components/page404/page404.component';
import { LoginComponent } from './components/login/login.component';
import { AdminMenuComponent } from './components/admin/admin-menu/admin-menu.component';
import { CompanyMenuComponent } from './components/company/company-menu/company-menu.component';
import { CustomerMenuComponent } from './components/customer/customer-menu/customer-menu.component';
import { AboutComponent } from './components/about/about.component';
import { AddCompanyComponent } from './components/admin/add-company/add-company.component';
import { UpdateCompanyComponent } from './components/admin/update-company/update-company.component';
import { DeleteCompanyComponent } from './components/admin/delete-company/delete-company.component';
import { AllCompaniesComponent } from './components/admin/all-companies/all-companies.component';
import { OneCompanyComponent } from './components/admin/one-company/one-company.component';
import { AddCustomerComponent } from './components/admin/add-customer/add-customer.component';
import { UpdateCustomerComponent } from './components/admin/update-customer/update-customer.component';
import { DeleteCustomerComponent } from './components/admin/delete-customer/delete-customer.component';
import { AllCustomersComponent } from './components/admin/all-customers/all-customers.component';
import { OneCustomerComponent } from './components/admin/one-customer/one-customer.component';
import { AddCouponComponent } from './components/company/add-coupon/add-coupon.component';
import { UpdateCouponComponent } from './components/company/update-coupon/update-coupon.component';
import { DeleteCouponComponent } from './components/company/delete-coupon/delete-coupon.component';
import { AllCompanyCouponsComponent } from './components/company/all-company-coupons/all-company-coupons.component';
import { AllCompanyCouponsByCategoryComponent } from './components/company/all-company-coupons-by-category/all-company-coupons-by-category.component';
import { AllCompanyCouponsByMaxPriceComponent } from './components/company/all-company-coupons-by-max-price/all-company-coupons-by-max-price.component';
import { CompanyDetailsComponent } from './components/company/company-details/company-details.component';
import { AllCustomerCouponsComponent } from './components/customer/all-customer-coupons/all-customer-coupons.component';
import { OneCompanyCouponComponent } from './components/company/one-company-coupon/one-company-coupon.component';
import { AllCustomerCouponsByCategoryComponent } from './components/customer/all-customer-coupons-by-category/all-customer-coupons-by-category.component';
import { AllCustomerCouponsByMaxPriceComponent } from './components/customer/all-customer-coupons-by-max-price/all-customer-coupons-by-max-price.component';
import { CustomerDetailsComponent } from './components/customer/customer-details/customer-details.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AllCouponsComponent } from './components/customer/all-coupons/all-coupons.component';

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    FooterComponent,
    MenuComponent,
    HomeComponent,
    Page404Component,
    LoginComponent,
    AdminMenuComponent,
    CompanyMenuComponent,
    CustomerMenuComponent,
    AboutComponent,
    AddCompanyComponent,
    UpdateCompanyComponent,
    DeleteCompanyComponent,
    AllCompaniesComponent,
    OneCompanyComponent,
    AddCustomerComponent,
    UpdateCustomerComponent,
    DeleteCustomerComponent,
    AllCustomersComponent,
    OneCustomerComponent,
    AddCouponComponent,
    UpdateCouponComponent,
    DeleteCouponComponent,
    AllCompanyCouponsComponent,
    OneCompanyCouponComponent,
    AllCompanyCouponsByCategoryComponent,
    AllCompanyCouponsByMaxPriceComponent,
    CompanyDetailsComponent,
    AllCustomerCouponsComponent,
    AllCustomerCouponsByCategoryComponent,
    AllCustomerCouponsByMaxPriceComponent,
    CustomerDetailsComponent,
    LogoutComponent,
    AllCouponsComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [LayoutComponent],
})
export class AppModule {}
