package system.core.daos;

import java.util.ArrayList;
import system.core.beans.Customer;
import system.core.exceptions.CouponSystemException;

public interface CustomersDAO {

	void addCustomer(Customer customer) throws CouponSystemException;

	boolean isCustomerExists(String custEmail, String custPass) throws CouponSystemException;

	void updateCustomer(Customer customer) throws CouponSystemException;

	void deleteCustomer(int custId) throws CouponSystemException;

	ArrayList<Customer> getAllCustomers() throws CouponSystemException;

	Customer getOneCustomerById(int custId) throws CouponSystemException;

	Customer getOneCustomerByEmail(String custEmail) throws CouponSystemException;

}
