package app.core;

import java.util.Collection;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.AdminService;

@SpringBootApplication
public class TestAdminService {

	public static void main(String[] args) throws CouponSystemException {

		ConfigurableApplicationContext ctx = SpringApplication.run(TestAdminService.class, args);

		LoginManager loginManager = ctx.getBean(LoginManager.class);

		// Test LoginManager
		AdminService adS = (AdminService) loginManager.login(
				"admin@admin.com", "admin", ClientType.ADMINISTRATOR);
		if (adS != null) {

		// Test addCompany from AdminService
//		Company comp1 = new Company("Space", "support@space-gym.com", "spspspsp");
//		adS.addCompany(comp1);

		// Test updateCompany from AdminService
//		Company comp2 = adS.getOneCompany(1L);
//		comp2.setEmail("support@wix.com");
//		comp2.setPassword("wxwxwxwx");
//		/* 
//		 * WILL NOT WORK DUE CUSTOM RESTRICTIONS - 
//		 * For more info: read updateCompany method documentation down below
//		 */
////		comp2.setName("Wix");
//		/*
//		 * WILL NOT WORK - Either updates a Company that holds the ID parameter you provided 
//		 * in the set method, or the method will not find a Company by the ID number you provided 
//		 * (throws CouponSystemException).
//		 */
////		comp2.setId(55L);
//		// SEND THE UPDATED COMPANY BACK TO THE DB
//		adS.updateCompany(comp2);

		// Test deleteCompany from AdminService
//		adS.deleteCompany(1L);

		// Test getAllCompanies from AdminService
//		List<Company> allCompanies = adS.getAllCompanies();
//		System.out.println("=========================");
//		System.out.println("All registered Companies (sorted by Companies Names):");
//		print(allCompanies);

		// Test getOneCompany from AdminService
//		Company comp3 = adS.getOneCompany(3L);
//		System.out.println("=========================");
//		System.out.println("Company info:");
//		System.out.println(comp3);
//		System.out.println("=========================");

		// Test addCustomer from AdminService
//		Customer cust1 = new Customer("Paz", "Noy", "paz@gmail.com", "pzpzpzpz");
//		adS.addCustomer(cust1);

		// Test updateCustomer from AdminService
//		Customer cust2 = adS.getOneCustomer(2L);
//		cust2.setEmail("kobi@gmail.com1111");
//		cust2.setPassword("kbkbkbkb1111");
//		cust2.setFirstName("Kobi1111");
//		cust2.setLastName("Bar-on1111");
//		/*
//		 * WILL NOT WORK - Either updates a Customer that holds the ID parameter you provided 
//		 * in the set method, or the method will not find a Customer by the ID number you provided 
//		 * (throws CouponSystemException).
//		 */
////		cust2.setId(55L);
//		// SEND THE UPDATED CUSTOMER BACK TO THE DB
//		adS.updateCustomer(cust2);

		// Test deleteCustomer from AdminService
//		adS.deleteCustomer(3L);

		// Test getAllCustomers from AdminService
//		List<Customer> allCustomers = adS.getAllCustomers();
//		System.out.println("=========================");
//		System.out.println("All registered Customers (sorted by Customers Last Names):");
//		print(allCustomers);

//		// Test getOneCustomer from AdminService
//		Customer cust3 = adS.getOneCustomer(4L);
//		System.out.println("=========================");
//		System.out.println("Customer info:");
//		System.out.println(cust3);
//		System.out.println("=========================");
			
		}
		
		System.out.println("Closing Application...");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ctx.close();
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
