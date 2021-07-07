package app.core.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

@Service
@Transactional
public class AdminService extends ClientService {

	public AdminService() {

	}

	public AdminService(CompanyRepository companyRepository, CustomerRepository customerRepository,
			CouponRepository couponRepository) {
		super();
		this.companyRepository = companyRepository;
		this.customerRepository = customerRepository;
		this.couponRepository = couponRepository;
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
	 * Saves/inserts a Company object into 'company' table in:
	 * 'coupons_springApp_db' database. This object will be inserted to the table
	 * only if the database isn't already contains a Company object that holds the
	 * same name or email parameters from the given Company object.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void addCompany(Company company) throws CouponSystemException {
		try {
			Optional<Company> opt = companyRepository.findByEmailOrNameIgnoreCase(company.getEmail(),
					company.getName());

			if (opt.isPresent()) {
				throw new CouponSystemException("A company with this Email/Name is already existed");
			} else {
				companyRepository.save(company);
				System.out.println("New Company was added:");
				System.out.println(company);
			}
		} catch (Exception e) {
			throw new CouponSystemException("addCompany from AdminService Failed", e);
		}
	}

	/**
	 * A method to update values of a specific Company object from DB. You can
	 * update the values of email and password parameters ONLY!
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void updateCompany(Company company) throws CouponSystemException {

		try {
			Optional<Company> opt = companyRepository.findById(company.getId());
			if (opt.isPresent()) {
				Company c = opt.get();
				c.setEmail(company.getEmail());
				c.setPassword(company.getPassword());
				companyRepository.save(c);
				System.out.println("Company was updated successfully:");
				System.out.println(c);
			} else {
				throw new CouponSystemException("Couldn't find any Company by this ID: " + company.getId());
			}
		} catch (Exception e) {
			throw new CouponSystemException("updateCompany from AdminService Failed", e);
		}
	}

	/**
	 * Deletes a Company object by the given id parameter. Deletes all records of
	 * the company coupons that were purchased by customers. Also deletes all
	 * created coupons by this company.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void deleteCompany(Long id) throws CouponSystemException {

		try {
			Optional<Company> opt = companyRepository.findById(id);
			if (opt.isPresent()) {
				Company c = opt.get();
				companyRepository.deleteById(id);
				System.out.println("All: '" + c.getName() + "' company records were deleted!");
			} else {
				throw new CouponSystemException("Couldn't find any Company by ID:" + id);
			}
		} catch (Exception e) {
			throw new CouponSystemException("deleteCompany from AdminService Failed", e);
		}

	}

	/**
	 * Gets the entire data from: 'company' table in: 'coupons_springApp_db'
	 * Database.
	 * 
	 * @return List
	 * @throws CouponSystemException
	 */
	public List<Company> getAllCompanies() throws CouponSystemException {

		try {
			List<Company> allCompanies = companyRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
			return allCompanies;
		} catch (Exception e) {
			throw new CouponSystemException("getAllCompanies from AdminService Failed", e);
		}
	}

	/**
	 * Receives a Company id parameter and seeks for a Company object by it, from:
	 * 'company' table in: 'coupons_springApp_db' Database.
	 * 
	 * @param
	 * @return Company
	 * @throws CouponSystemException
	 */
	public Company getOneCompany(Long id) throws CouponSystemException {

		try {
			Optional<Company> opt = companyRepository.findById(id);
			if (opt.isPresent()) {
				Company c = opt.get();
				return c;
			} else {
				throw new CouponSystemException("Couldn't find any company by ID: " + id);
			}
		} catch (Exception e) {
			throw new CouponSystemException("getOneCompany from AdminService Failed", e);
		}
	}

	/**
	 * Saves/inserts a Customer object into 'customer' table in:
	 * 'coupons_springApp_db' database. This object will be inserted to the table
	 * only if the database isn't already contains a Customer object that holds the
	 * same email parameter from the given Customer object.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void addCustomer(Customer customer) throws CouponSystemException {

		try {
			Optional<Customer> opt = customerRepository.findByEmailIgnoreCase(customer.getEmail());
			if (opt.isPresent()) {
				throw new CouponSystemException("A Customer with this Email is already existed in DB");
			} else {
				customerRepository.save(customer);
				System.out.println("New Customer was added:");
				System.out.println(customer);
			}

		} catch (Exception e) {
			throw new CouponSystemException("addCustomer from AdminService Failed", e);
		}
	}

	/**
	 * An access method to update values of a specific Customer object. You can't
	 * update the value of customer id!
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void updateCustomer(Customer customer) throws CouponSystemException {

		try {
			Optional<Customer> opt = customerRepository.findById(customer.getId());
			if (opt.isPresent()) {
				Customer c = opt.get();
				c.setFirstName(customer.getFirstName());
				c.setLastName(customer.getLastName());
				c.setEmail(customer.getEmail());
				c.setPassword(customer.getPassword());
				customerRepository.save(c);
				System.out.println("Customer was updated successfully:");
				System.out.println(c);
			} else {
				throw new CouponSystemException("Couldn't find any Customer by this ID: " + customer.getId());
			}
		} catch (Exception e) {
			throw new CouponSystemException("updateCustomer from AdminService Failed", e);
		}

	}

	/**
	 * Deletes a Customer object by the given Customer id parameter. Deletes all
	 * records of Coupons that were purchased by the given Customer id and returns
	 * them to their owner company (increasing the amount by 1 for each Coupon
	 * element).
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void deleteCustomer(Long id) throws CouponSystemException {

		try {
			Optional<Customer> opt = customerRepository.findById(id);
			if (opt.isPresent()) {
				Customer c = opt.get();
				List<Coupon> customerCoupons = c.getCoupons();
				System.out.println(
						"Returns the Coupons that were purchased by the deleted Customer" + " to their Companies...");
				for (Coupon coupon : customerCoupons) {
					coupon.setAmount(coupon.getAmount() + 1);
					couponRepository.save(coupon);
				}
				customerRepository.deleteById(id);
				System.out.println("All: '" + c.getFirstName() + " " + c.getLastName() + "' records were deleted!");
			} else {
				throw new CouponSystemException("Couldn't find any Customer by ID:" + id);
			}
		} catch (Exception e) {
			throw new CouponSystemException("deleteCustomer from AdminService Failed", e);
		}

	}

	/**
	 * Gets the entire data from: 'customer' table in: 'coupons_springApp_db'
	 * Database.
	 * 
	 * @return List
	 * @throws CouponSystemException
	 */
	public List<Customer> getAllCustomers() throws CouponSystemException {

		try {
			List<Customer> allCustomers = customerRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
			return allCustomers;
		} catch (Exception e) {
			throw new CouponSystemException("getAllCustomers from AdminService Failed", e);
		}
	}

	/**
	 * Receives a Customer id parameter and seeks for a Customer object by it, from:
	 * 'customer' table in: 'coupons_springApp_db' Database.
	 * 
	 * @param
	 * @return Customer
	 * @throws CouponSystemException
	 */
	public Customer getOneCustomer(Long id) throws CouponSystemException {

		try {
			Optional<Customer> opt = customerRepository.findById(id);
			if (opt.isPresent()) {
				Customer c = opt.get();
				return c;
			} else {
				throw new CouponSystemException("Couldn't find any customer by ID: " + id);
			}
		} catch (Exception e) {
			throw new CouponSystemException("getOneCustomer from AdminService Failed", e);
		}

	}
}
