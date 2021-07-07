package system.core.daos;

import system.core.beans.Customer;
import system.core.exceptions.CouponSystemException;

public class TestCustomersDAO {

	public static void main(String[] args) throws CouponSystemException {

		CustomersDAO dao = new CustomersDBDAO();

		// Test addCustomer
//		Customer c1 = new Customer(0, "Kobi", "Bar-on", "kobi@gmail.com", "kbkbkbkb");
//		dao.addCustomer(c1);

		// Test isCustomerExists
//		System.out.println("===========");
//		dao.isCustomerExists("hoekanat@gmail.com", "anat2406");
//		System.out.println("===========");

		// Test updateCustomer
//		Customer c2 = new Customer(1, "anat", "hoek", "hoekanat@gmail.com", "anat2406");
//		dao.updateCustomer(c2);

		// Test deleteCustomer
//		dao.deleteCustomer(2);

		// Test getAllCustomer
//		dao.getAllCustomers();

		// Test getOneCustomer
//		dao.getOneCustomerById(3);

	}

}
