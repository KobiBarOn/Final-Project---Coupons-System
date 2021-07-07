package system.core.clients;

import java.util.ArrayList;

import system.core.beans.Company;
import system.core.beans.Coupon;
import system.core.beans.CouponWithCustId;
import system.core.beans.Customer;
import system.core.exceptions.CouponSystemException;

public class AdminFacade extends ClientFacade {

	public AdminFacade() {

	}

	@Override
	public boolean login(String email, String password) {

		if (email.equalsIgnoreCase("admin@admin.com") && password.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates a Company object and insert it (with his values) into 'companies'
	 * table in: 'coupons_project_db' Database. This method will work only if the
	 * compName and compEmail parameters from the given Company Object will hold a
	 * different values.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void addCompany(Company company) throws CouponSystemException {
		try {

			if (companiesDAO.isCompanyExistsByNameOrEmail(company.getCompEmail(), company.getCompName())) {
				System.out.println("Error: a company with this Email/Name is already existed");
			} else {
				companiesDAO.addCompany(company);
			}
		} catch (Exception e) {
			throw new CouponSystemException("addCompany of AdminFacade Failed", e);
		}
	}

	/**
	 * An access method to update values of a specific Company object. You can ONLY
	 * update the values of compEmail and compPass!
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void updateCompany(Company company) throws CouponSystemException {

		try {
			Company temp = companiesDAO.getOneCompanyById(company.getCompId());
			temp.setCompEmail(company.getCompEmail());
			temp.setCompPass(company.getCompPass());
			companiesDAO.updateCompany(temp);
			System.out.println("Company was updated successfully:");
			System.out.println(temp);
		} catch (Exception e) {
			throw new CouponSystemException("updateCompany of AdminFacade Failed", e);
		}
	}

	/**
	 * Deletes a Company object by the given compId parameter. Deletes all records
	 * of the company coupons that were purchased by customers. Also deletes all
	 * created coupons by this company.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void deleteCompany(int compId) throws CouponSystemException {

		try {
			ArrayList<CouponWithCustId> allCoupons = couponsDAO.getCouponCustIdByCompId(compId);
			for (CouponWithCustId coup : allCoupons) {
				couponsDAO.deleteCouponByCompId(compId);
				couponsDAO.deleteCouponPurchase(coup.getCustId(), coup.getCoupId());
			}
			companiesDAO.deleteCompany(compId);
			System.out.println("All: " + compId + "(ID) company records were deleted!");
		} catch (Exception e) {
			throw new CouponSystemException("deleteCompany of AdminFacade Failed", e);
		}

	}

	/**
	 * Gets the entire 'companies' table from: 'coupons_project_db' Database.
	 * 
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	public ArrayList<Company> getAllCompanies() throws CouponSystemException {

		try {
			ArrayList<Company> allCompanies = companiesDAO.getAllCompanies();
			return allCompanies;
		} catch (Exception e) {
			throw new CouponSystemException("getAllCompanies of AdminFacade Failed", e);
		}
	}

	/**
	 * Receives a 'compId' parameters and Seeks for a Company object by it, in:
	 * 'companies' table in: 'coupons_project_db' Database.
	 * 
	 * @param
	 * @return Company
	 * @throws CouponSystemException
	 */
	public Company getOneCompany(int compId) throws CouponSystemException {

		try {
			Company comp = companiesDAO.getOneCompanyById(compId);
			System.out.println(comp);
			return comp;

		} catch (Exception e) {
			throw new CouponSystemException("getOneCompany of AdminFacade Failed", e);
		}
	}

	/**
	 * Creates a Customer object and insert it (with his values) into 'customers'
	 * table in: 'coupons_project_db' Data Base. This method will work only if the
	 * custEmail parameter from the given Customer Object will hold a different
	 * value. For more info: view: hash code and equals override method in
	 * CouponsApp.src.system.core.Customer.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void addCustomer(Customer customer) throws CouponSystemException {

		try {
			Customer temp = new Customer();
			ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();
			for (Customer cust : allCustomers) {
				if (!cust.equals(customer)) {
					temp = customer;
					continue;
				} else {
					temp = null;
					System.out.println("Error: a customer with this Email is already existed");
					break;
				}
			}
			customersDAO.addCustomer(temp);
		} catch (Exception e) {
			throw new CouponSystemException("addCustomer of AdminFacade Failed", e);
		}
	}

	/**
	 * An access method to update values of a specific Customer object. You can't
	 * update the value of custId!
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void updateCustomer(Customer customer) throws CouponSystemException {

		try {
			Customer temp = customersDAO.getOneCustomerById(customer.getCustId());
			temp.setFirstName(customer.getFirstName());
			temp.setLastName(customer.getLastName());
			temp.setCustEmail(customer.getCustEmail());
			temp.setCustPass(customer.getCustPass());
			customersDAO.updateCustomer(customer);
			System.out.println("Customer was updated successfully:");
			System.out.println(temp);
		} catch (Exception e) {
			throw new CouponSystemException("updateCustomer of AdminFacade Failed", e);
		}

	}

	/**
	 * Deletes a Customer object by the given custId parameter. Deletes all records
	 * of coupons purchases that were created by this Customer.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void deleteCustomer(int custId) throws CouponSystemException {

		try {
			ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
			for (Coupon coup : allCoupons) {
				couponsDAO.deleteCouponPurchase(custId, coup.getCoupId());
			}
			customersDAO.deleteCustomer(custId);
			System.out.println("All: " + custId + "(ID) customer records were deleted!");
		} catch (Exception e) {
			throw new CouponSystemException("deleteCustomer of AdminFacade Failed", e);
		}

	}

	/**
	 * Gets the entire 'customers' table from: 'coupons_project_db' Database.
	 * 
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	public ArrayList<Customer> getAllCustomers() throws CouponSystemException {

		try {
			ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();
			return allCustomers;
		} catch (Exception e) {
			throw new CouponSystemException("getAllCustomers of AdminFacade Failed", e);
		}
	}

	/**
	 * Receives a custId parameter and Seeks for a matching Customer object it, in:
	 * 'customers' table, in: 'coupons_project_db' Database.
	 * 
	 * @param
	 * @return Customer
	 * @throws CouponSystemException
	 */
	public Customer getOneCustomer(int custId) throws CouponSystemException {

		try {
			return customersDAO.getOneCustomerById(custId);
		} catch (Exception e) {
			throw new CouponSystemException("getOneCustomer of AdminFacade Failed", e);
		}

	}
}
