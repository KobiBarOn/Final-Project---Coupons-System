package app.core.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CustomerRepository;

@Service
@Transactional
public class AdminService extends ClientService {

	public AdminService() {

	}

	public AdminService(CompanyRepository companyRepository, CustomerRepository customerRepository) {
		super();
		this.companyRepository = companyRepository;
		this.customerRepository = customerRepository;
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
	public Company addCompany(Company company) throws CouponSystemException {
		try {
			Optional<Company> opt = companyRepository.findByEmailOrNameIgnoreCase(company.getEmail(),
					company.getName());

			if (opt.isPresent()) {
				throw new CouponSystemException("A company with this Email/Name is already existed");
			} else {
				System.out.println("New Company was added:");
				System.out.println(companyRepository.save(company));
				return company;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
		}
	}

	/**
	 * A method to update values of a specific Company object from DB. You can
	 * update the values of email and password parameters ONLY!
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public Company updateCompany(Company company) throws CouponSystemException {

		try {
			Optional<Company> opt = companyRepository.findById(company.getId());
			if (opt.isPresent()) {
				Company c = opt.get();
				c.setEmail(company.getEmail());
				c.setPassword(company.getPassword());
				System.out.println("Company was updated successfully:");
				System.out.println(companyRepository.save(c));
				return c;
			} else {
				throw new CouponSystemException("Couldn't find any Company to update by this ID: " + company.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
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
	public Long deleteCompany(Long id) throws CouponSystemException {

		try {
			Optional<Company> opt = companyRepository.findById(id);
			if (opt.isPresent()) {
				Company c = opt.get();
				companyRepository.deleteById(id);
				System.out.println("All: '" + c.getName() + "' company records were deleted!");
				return c.getId();
			} else {
				throw new CouponSystemException("Couldn't find any Company by ID:" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
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
			if (allCompanies.isEmpty()) {
				throw new CouponSystemException("No Company/ies were registered to our system");
			} else {
				return allCompanies;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
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
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
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
	public Customer addCustomer(Customer customer) throws CouponSystemException {

		try {
			Optional<Customer> opt = customerRepository.findByEmailIgnoreCase(customer.getEmail());
			if (opt.isPresent()) {
				throw new CouponSystemException("A Customer with this Email is already existed in DB");
			} else {
				System.out.println("New Customer was added:");
				System.out.println(customerRepository.save(customer));
				return customer;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
		}
	}

	/**
	 * An access method to update values of a specific Customer object. You can't
	 * update the value of customer id!
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public Customer updateCustomer(Customer customer) throws CouponSystemException {

		try {
			Optional<Customer> opt = customerRepository.findById(customer.getId());
			if (opt.isPresent()) {
				Customer c = opt.get();
				c.setFirstName(customer.getFirstName());
				c.setLastName(customer.getLastName());
				c.setEmail(customer.getEmail());
				c.setPassword(customer.getPassword());
				System.out.println("Customer was updated successfully:");
				System.out.println(customerRepository.save(c));
				return c;
			} else {
				throw new CouponSystemException("Couldn't find any Customer to update by this ID: " + customer.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
		}

	}

	/**
	 * Deletes a Customer object by the given Customer id parameter. Also deletes
	 * all of his purchase history.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public Long deleteCustomer(Long id) throws CouponSystemException {

		try {
			Optional<Customer> opt = customerRepository.findById(id);
			if (opt.isPresent()) {
				Customer c = opt.get();
				customerRepository.deleteById(id);
				System.out.println("All: '" + c.getFirstName() + " " + c.getLastName() + "' records were deleted!");
				return c.getId();
			} else {
				throw new CouponSystemException("Couldn't find any Customer by ID:" + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
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
			if (allCustomers.isEmpty()) {
				throw new CouponSystemException("No Customer/s were registered to our system");
			} else {
				return allCustomers;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
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
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
		}

	}
}
