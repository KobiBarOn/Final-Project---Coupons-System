package app.core;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;

@Component
public class Test {

	@Autowired
	private LoginManager loginManager;
	

	public void testAll() throws CouponSystemException {

		try {

			{
//			=============== Test Admin Service ===============
					
				// Test LoginManager
					AdminService adS = (AdminService) loginManager.login(
							"admin@admin.com", "admin", ClientType.ADMINISTRATOR);
					if (adS != null) {

					// Test addCompany
					Company comp1 = new Company("Space", "support@space-gym.com", "spspspsp");
					adS.addCompany(comp1);

					// Test updateCompany
					Company comp2 = adS.getOneCompany(1L);
					comp2.setEmail("support@wix.com");
					comp2.setPassword("wxwxwxwx");
					/* 
					 * WILL NOT WORK DUE CUSTOM RESTRICTIONS - 
					 * For more info: read updateCompany method documentation down below
					 */
//					comp2.setName("Wix");
					/*
					 * WILL NOT WORK - Either updates a Company that holds the ID parameter you provided 
					 * in the set method, or the method will not find a Company by the ID number you provided 
					 * (throws CouponSystemException).
					 */
//					comp2.setId(55L);
					// SEND THE UPDATED COMPANY BACK TO THE DB
					adS.updateCompany(comp2);

					// Test deleteCompany
					adS.deleteCompany(1L);

					// Test getAllCompanies
					List<Company> allCompanies = adS.getAllCompanies();
					System.out.println("=========================");
					System.out.println("All registered Companies (sorted by Companies Names):");
					print(allCompanies);

					// Test getOneCompany
					Company comp3 = adS.getOneCompany(3L);
					System.out.println("=========================");
					System.out.println("Company info:");
					System.out.println(comp3);
					System.out.println("=========================");

					// Test addCustomer
					Customer cust1 = new Customer("Paz", "Noy", "paz@gmail.com", "pzpzpzpz");
					adS.addCustomer(cust1);

					// Test updateCustomer from AdminService
					Customer cust2 = adS.getOneCustomer(2L);
					cust2.setEmail("kobi@gmail.com1111");
					cust2.setPassword("kbkbkbkb1111");
					cust2.setFirstName("Kobi1111");
					cust2.setLastName("Bar-on1111");
					/*
					 * WILL NOT WORK - Either updates a Customer that holds the ID parameter you provided 
					 * in the set method, or the method will not find a Customer by the ID number you provided 
					 * (throws CouponSystemException).
					 */
//					cust2.setId(55L);
					// SEND THE UPDATED CUSTOMER BACK TO THE DB
					adS.updateCustomer(cust2);

					// Test deleteCustomer
					adS.deleteCustomer(3L);

					// Test getAllCustomers
					List<Customer> allCustomers = adS.getAllCustomers();
					System.out.println("=========================");
					System.out.println("All registered Customers (sorted by Customers Last Names):");
					print(allCustomers);

//					// Test getOneCustomer
					Customer cust3 = adS.getOneCustomer(4L);
					System.out.println("=========================");
					System.out.println("Customer info:");
					System.out.println(cust3);
					System.out.println("=========================");
						
					}
				}

				{
//			=============== Test Company Service ===============
					// Test LoginManager
					CompanyService compS = (CompanyService) loginManager.login(
							"support@shila-rest.com", "shshshsh", ClientType.COMPANY);
					if (compS != null) {

						final Company loggedInCompany = compS.getCompanyDetails();

					// Test addCoupon		
					LocalDate startDate1 = LocalDate.of(2021, 3, 20);
					Date startSqlDate1 = Date.valueOf(startDate1);
			
					LocalDate endDate1 = LocalDate.of(2021, 6, 4);
					Date endSqlDate1 = Date.valueOf(endDate1);
			
					Coupon coup1 = new Coupon(Category.GYM, "Yoga", "2 Yoga lessons in a price of 1", startSqlDate1, endSqlDate1, 350, 50,
							"2 Yoga lessons in 50₪ ONLY", loggedInCompany);
					compS.addCoupon(coup1);

					// Test updateCoupon
					LocalDate startDate2 = LocalDate.of(2021, 3, 11);
					Date startSqlDate2 = Date.valueOf(startDate2);
			
					LocalDate endDate2 = LocalDate.of(2021, 5, 16);
					Date endSqlDate2 = Date.valueOf(endDate2);
					
					Coupon coup2 = compS.getOneCoupon(11L);
					coup2.setCategory(Category.ELECTRONICS);
					coup2.setTitle("Yoga1111");
					coup2.setDescription("2 Yoga lessons in a price of 1111111");
					coup2.setStartDate(startSqlDate2);
					coup2.setEndDate(endSqlDate2);
					coup2.setAmount(900);
					coup2.setPrice(300);
					coup2.setImage("2 Yoga lessons in 50₪ ONLY1111");
					// WILL NOT WORK - throws CouponSystemException of: "not owned Coupon"/"Couldn't find Coupon".
//					coup2.setId(3L);
					// WILL WORK (will update all other parameters) BUT WILL NOT update the owning Company!
//					Company comp4 = new Company("Example Company", "example@mail.com", "1234");
//					coup2.setCompany(comp4);
					// SEND THE UPDATED COUPON BACK TO THE DB 
					compS.updateCoupon(coup2);

					// Test deleteCoupon
					compS.deleteCoupon(3L);

//					// Test getOneCoupon
					Coupon coup3 = compS.getOneCoupon(8L);
					System.out.println("=========================");
					System.out.println("Coupon info:");
					System.out.println(coup3);
					System.out.println("=========================");

					// Test getCompanyCoupons
					List<Coupon> compCoupons = compS.getCompanyCoupons();
					System.out.println("=========================");
					System.out.println("All your Company Coupons:");
					print(compCoupons);

					// Test getCompanyCoupons By Category
					Category category1 = Category.ELECTRONICS;
					List<Coupon> compCouponsByCategory = compS.getCompanyCoupons(category1);
					System.out.println("=========================");
					System.out.println("Your Company Coupons By Category: " + category1);
					print(compCouponsByCategory);

					// Test getCompanyCoupons By MAX Price
					double maxPrice1 = 20;
					List<Coupon> compCouponsByMaxPrice = compS.getCompanyCoupons(maxPrice1);
					System.out.println("=========================");
					System.out.println("Your Company Coupons By MAX Price: " + maxPrice1);
					print(compCouponsByMaxPrice);

					// Test getCompanyDetails from CompanyService
					Company comp5 = compS.getCompanyDetails();
					System.out.println("=========================");
					System.out.println("Your Company Details:");
					System.out.println(comp5);
					System.out.println("=========================");
					
					}
				}

				{
//			=============== Test Customer Service ===============
					// Test LoginManager
					CustomerService custS = (CustomerService) loginManager.login(
							"paz@gmail.com", "pzpzpzpz", ClientType.CUSTOMER);
					if (custS != null) {

					// Test purchaseCoupon from CustomerService
					Coupon coup4 = custS.getOneCoupon(9L);
					System.out.println("======================");
					System.out.println("Examining your Coupon purchase request: ");
					System.out.println(coup4);
					System.out.println("======================");
					custS.purchaseCoupon(coup4);
						
					// Test getCustomerCoupons from CustomerService
					List<Coupon> custCoupons = custS.getCustomerCoupons();
					System.out.println("=========================");
					System.out.println("Your Coupon purchase history:");
					print(custCoupons);
						
					// Test getOneCoupon from CustomerService
					Coupon coup5 = custS.getOneCoupon(10L);
					System.out.println("=========================");
					System.out.println("Coupon info:");
					System.out.println(coup5);
					System.out.println("=========================");

					// Test getCustomerCoupons By Category from CustomerService
					Category category2 = Category.ELECTRONICS;
					List<Coupon> custCouponsByCategory = custS.getCustomerCoupons(category2);
					System.out.println("=========================");
					System.out.println("Your Coupons By Category: " + category2);
					print(custCouponsByCategory);

					// Test getCustomerCoupons By MAX Price from CustomerService
					double maxPrice2 = 500;
					List<Coupon> custCouponsByMaxPrice = custS.getCustomerCoupons(maxPrice2);
					System.out.println("=========================");
					System.out.println("Your Coupons By MAX Price: " + maxPrice2);
					print(custCouponsByMaxPrice);

					// Test getCustomerDetails from CustomerService
					Customer cust4 = custS.getCustomerDetails();
					System.out.println("=========================");
					System.out.println("Your Details:");
					System.out.println(cust4);
					System.out.println("=========================");
						
					}
				}
		
		} catch (Exception e) {
			throw new CouponSystemException("Test Failed", e);
		}
	}

	public static void print(Collection<?> col) {
		System.out.println("=========================");
		if (!col.isEmpty()) {
			for (Object e : col) {
				System.out.println(e);
			}
		} else {
			System.out.println("This Collection is Empty!");
		}
		System.out.println("=========================");
	}
	
}
