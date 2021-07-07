import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { AddCompanyComponent } from './components/admin/add-company/add-company.component';
import { AddCouponComponent } from './components/company/add-coupon/add-coupon.component';
import { AddCustomerComponent } from './components/admin/add-customer/add-customer.component';
import { AdminMenuComponent } from './components/admin/admin-menu/admin-menu.component';
import { AllCompaniesComponent } from './components/admin/all-companies/all-companies.component';
import { AllCompanyCouponsByCategoryComponent } from './components/company/all-company-coupons-by-category/all-company-coupons-by-category.component';
import { AllCompanyCouponsByMaxPriceComponent } from './components/company/all-company-coupons-by-max-price/all-company-coupons-by-max-price.component';
import { AllCompanyCouponsComponent } from './components/company/all-company-coupons/all-company-coupons.component';
import { AllCouponsComponent } from './components/customer/all-coupons/all-coupons.component';
import { AllCustomerCouponsByCategoryComponent } from './components/customer/all-customer-coupons-by-category/all-customer-coupons-by-category.component';
import { AllCustomerCouponsByMaxPriceComponent } from './components/customer/all-customer-coupons-by-max-price/all-customer-coupons-by-max-price.component';
import { AllCustomerCouponsComponent } from './components/customer/all-customer-coupons/all-customer-coupons.component';
import { AllCustomersComponent } from './components/admin/all-customers/all-customers.component';
import { CompanyDetailsComponent } from './components/company/company-details/company-details.component';
import { CompanyMenuComponent } from './components/company/company-menu/company-menu.component';
import { CustomerDetailsComponent } from './components/customer/customer-details/customer-details.component';
import { CustomerMenuComponent } from './components/customer/customer-menu/customer-menu.component';
import { DeleteCompanyComponent } from './components/admin/delete-company/delete-company.component';
import { DeleteCouponComponent } from './components/company/delete-coupon/delete-coupon.component';
import { DeleteCustomerComponent } from './components/admin/delete-customer/delete-customer.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { OneCompanyCouponComponent } from './components/company/one-company-coupon/one-company-coupon.component';
import { OneCompanyComponent } from './components/admin/one-company/one-company.component';
import { OneCustomerComponent } from './components/admin/one-customer/one-customer.component';
import { Page404Component } from './components/page404/page404.component';
import { UpdateCompanyComponent } from './components/admin/update-company/update-company.component';
import { UpdateCouponComponent } from './components/company/update-coupon/update-coupon.component';
import { UpdateCustomerComponent } from './components/admin/update-customer/update-customer.component';
import { MenuGuard } from './guards/menu.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'about', component: AboutComponent },
  { path: 'home', component: HomeComponent },
  { path: 'logout', component: LogoutComponent },
  {
    path: 'admin-menu',
    component: AdminMenuComponent,
    canActivate: [MenuGuard],
    canActivateChild: [MenuGuard],
    canDeactivate: [MenuGuard],
    canLoad: [MenuGuard],
    children: [
      { path: 'add-company', component: AddCompanyComponent },
      { path: 'update-company', component: UpdateCompanyComponent },
      { path: 'delete-company', component: DeleteCompanyComponent },
      { path: 'all-companies', component: AllCompaniesComponent },
      { path: 'one-company', component: OneCompanyComponent },
      { path: 'add-customer', component: AddCustomerComponent },
      { path: 'update-customer', component: UpdateCustomerComponent },
      { path: 'delete-customer', component: DeleteCustomerComponent },
      { path: 'all-customers', component: AllCustomersComponent },
      { path: 'one-customer', component: OneCustomerComponent },
    ],
  },
  {
    path: 'company-menu',
    component: CompanyMenuComponent,
    canActivate: [MenuGuard],
    canActivateChild: [MenuGuard],
    canDeactivate: [MenuGuard],
    canLoad: [MenuGuard],
    children: [
      { path: 'add-coupon', component: AddCouponComponent },
      { path: 'update-coupon', component: UpdateCouponComponent },
      { path: 'delete-coupon', component: DeleteCouponComponent },
      { path: 'all-company-coupons', component: AllCompanyCouponsComponent },
      { path: 'one-company-coupon', component: OneCompanyCouponComponent },
      {
        path: 'all-company-coupons-by-category',
        component: AllCompanyCouponsByCategoryComponent,
      },
      {
        path: 'all-company-coupons-by-max-price',
        component: AllCompanyCouponsByMaxPriceComponent,
      },
      { path: 'company-details', component: CompanyDetailsComponent },
    ],
  },
  {
    path: 'customer-menu',
    component: CustomerMenuComponent,
    canActivate: [MenuGuard],
    canActivateChild: [MenuGuard],
    canDeactivate: [MenuGuard],
    canLoad: [MenuGuard],
    children: [
      { path: 'all-coupons', component: AllCouponsComponent },
      { path: 'all-customer-coupons', component: AllCustomerCouponsComponent },
      {
        path: 'all-customer-coupons-by-category',
        component: AllCustomerCouponsByCategoryComponent,
      },
      {
        path: 'all-customer-coupons-by-max-price',
        component: AllCustomerCouponsByMaxPriceComponent,
      },
      { path: 'customer-details', component: CustomerDetailsComponent },
    ],
  },

  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: Page404Component },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
