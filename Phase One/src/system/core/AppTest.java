package system.core;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import system.core.beans.Category;
import system.core.beans.Company;
import system.core.beans.Coupon;
import system.core.beans.Customer;
import system.core.clients.AdminFacade;
import system.core.clients.ClientType;
import system.core.clients.CompanyFacade;
import system.core.clients.CustomerFacade;
import system.core.clients.LoginManager;
import system.core.connection.ConnectionPool;
import system.core.daos.CouponsDAO;
import system.core.daos.CouponsDBDAO;
import system.core.exceptions.CouponSystemException;
import system.core.thread.CouponExpirationDailyJob;

public class AppTest {

	LoginManager lm = LoginManager.getInstance();
	CouponsDAO dao = new CouponsDBDAO();
	CouponExpirationDailyJob r = new CouponExpirationDailyJob();
	Thread t = new Thread(r, "t");

	public void testAll() throws CouponSystemException {

		try {

			{
				while (r.isQuit() == false) {
//			=============== Start Daily Job =================
					t.start();
//			=============== Test Admin Facade ===============
					// Test LoginManager
					if (lm.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR) != null) {

						AdminFacade adf = (AdminFacade) lm.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
						System.out.println("Administrator is logged-in");

						// Test addCompany
						Company c1 = new Company(0, "Apple", "Apple@support.com", "apapapap");
						adf.addCompany(c1);

						// Test updateCompany
						Company c2 = new Company(1, "Apple", "Apple@NewEmail.com", "NewPassForApple");
						adf.updateCompany(c2);

						// Test deleteCompany
						adf.deleteCompany(1);

						// Test getAllCompanies
						List<Company> companies = adf.getAllCompanies();
						print(companies);

						// Test getOneCompany
						adf.getOneCompany(1);

						// Test addCustomer
						Customer cust1 = new Customer(0, "Dor", "Cohen", "dorC@gmail.com", "dor12345");
						adf.addCustomer(cust1);

						// Test updateCustomer
						Customer cust2 = new Customer(1, "Dori", "Rabinovich", "dorR@walla.com", "12345dor");
						adf.updateCustomer(cust2);

						// Test deleteCustomer
						adf.deleteCustomer(1);

						// Test getAllCustomers
						List<Customer> customers = adf.getAllCustomers();
						print(customers);

						// Test getOneCustomer
						adf.getOneCustomer(1);
					}
				}

				{
//			=============== Test Company Facade ===============
					// Test LoginManager
					if (lm.login("Apple@NewEmail.com", "NewPassForApple", ClientType.COMPANY) != null) {

						CompanyFacade cof = (CompanyFacade) lm.login("admin@admin.com", "admin",
								ClientType.ADMINISTRATOR);
						System.out.println("Company is logged-in");

						// Test addCoupon
						LocalDate startDate1 = LocalDate.of(2020, 12, 8);
						Date startSqlDate1 = Date.valueOf(startDate1);

						LocalDate endDate1 = LocalDate.of(2020, 12, 28);
						Date endSqlDate1 = Date.valueOf(endDate1);

						Coupon c1 = new Coupon(0, 1, Category.FOOD, "Pork", "Beacon", startSqlDate1, endSqlDate1, 15,
								5.5, "Joicy Beacon");
						cof.addCoupon(c1);

						// Test updateCoupon
						LocalDate startDate2 = LocalDate.of(2020, 12, 8);
						Date startSqlDate2 = Date.valueOf(startDate2);

						LocalDate endDate2 = LocalDate.of(2020, 12, 29);
						Date endSqlDate2 = Date.valueOf(endDate2);
						Coupon c2 = new Coupon(1, 1, Category.GYM, "2In1", "2 Yoga classes for a price of 1",
								startSqlDate2, endSqlDate2, 10, 20, "Best GYM Ever");
						cof.updateCoupon(c2);

						// Test deleteCoupon
						cof.deleteCoupon(1);

						// Test getCompanyCoupons
						List<Coupon> compCoupons = cof.getCompanyCoupons();
						print(compCoupons);

						// Test getCompanyCoupons By Category
						List<Coupon> compCouponsByCategory = cof.getCompanyCoupons(Category.FOOD);
						print(compCouponsByCategory);

						// Test getCompanyCoupons By MAX Price of Company
						List<Coupon> compCouponsByMaxPrice = cof.getCompanyCoupons(6);
						print(compCouponsByMaxPrice);

						// Test getCompanyDetails
						Company comp = cof.getCompanyDetails();
						System.out.println(comp);
					}
				}

				{
//			=============== Test Customer Facade ===============
					// Test LoginManager
					if (lm.login("dorR@walla.com", "12345dor", ClientType.CUSTOMER) != null) {

						CustomerFacade csf = (CustomerFacade) lm.login("admin@admin.com", "admin",
								ClientType.ADMINISTRATOR);
						System.out.println("Customer is logged-in");

						// Test purchaseCoupon
						Coupon c = dao.getOneCoupon(1);
						if (c != null) {
							csf.purchaseCoupon(c);
						}

						// Test getCustomerCoupons
						List<Coupon> custCoupons = csf.getCustomerCoupons();
						print(custCoupons);

						// Test getCustomerCoupons By Category
						List<Coupon> custCouponsByCategory = csf.getCustomerCoupons(Category.FOOD);
						print(custCouponsByCategory);

						// Test getCustomerCoupons By MAX Price
						List<Coupon> custCouponsByMaxPrice = csf.getCustomerCoupons(6);
						print(custCouponsByMaxPrice);

						// Test getCustomerDetails
						Customer cust = csf.getCustomerDetails();
						System.out.println(cust);
					}
				}
			}
		} catch (Exception e) {
			throw new CouponSystemException("AppTest Failed", e);
		} finally {
			r.stop();
			ConnectionPool.getInstance().closeAllConnections();
			System.out.println("=== App ShutDown ===");
			System.out.println("=== GoodBye and Hope to see you Soon :) ===");
		}

	}

	public static void print(Collection<?> col) {
		System.out.println("=========================");
		if (col != null) {
			for (Object e : col) {
				System.out.println(e);
			}
		} else {
			System.out.println("This Collection is Empty!");
		}
		System.out.println("=========================");
	}
}
