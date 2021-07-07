package system.core.clients;

import java.util.Collection;
import java.util.List;
import system.core.beans.Company;
import system.core.beans.Customer;
import system.core.exceptions.CouponSystemException;

public class TestAdminFacade {

	public static void main(String[] args) throws CouponSystemException {

		LoginManager lm = LoginManager.getInstance();
		
//		 Test loginManager
		if(lm.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR) != null) {
			
		AdminFacade adf = (AdminFacade) lm.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
		System.out.println("Administrator is logged-in");
		
		// Test addCompany of AdminFacade
//		Company c1 = new Company(0, "google", "support@google.com", "apapapa");
//		adf.addCompany(c1);
		
		// Test updateCompany of AdminFacade
//		System.out.println("Please enter the Company ID and name that you want to update her Email and password:");
//		Company c2 = new Company(1, "teva", "support@teva2.com", "tvtvtvtv1");
//		adf.updateCompany(c2);
		
		// Test deleteCompany of AdminFacade
//		adf.deleteCompany(31);
		
		// Test getAllCompanies of AdminFacade 
//		List<Company> companies = adf.getAllCompanies();
//		print(companies);
		
		// Test getOneCompany of AdminFacade
//		adf.getOneCompany(30);
		
		// Test addCustomer of AdminFacade
//		Customer c = new Customer(0, "Dor", "Cohen", "kobi@gmail.com5", "kbkbk123");
//		adf.addCustomer(c);
		
		// Test updateCustomer of AdminFacade
//		System.out.println("Please enter the Customer ID that you want to update his/her details:");
//		Customer c = new Customer(20, "Dori", "Rabinovich", "dor@gmail.com", "dor1234");
//		adf.updateCustomer(c);
		
		// Test deleteCustomer of AdminFacade
//		adf.deleteCustomer(19);
		
		// Test getAllCustomers of AdminFacade
//		List<Customer> customers = adf.getAllCustomers();
//		print(customers);
	
		// Test getOneCustomer of AdminFacade
//		adf.getOneCustomer(14);
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
