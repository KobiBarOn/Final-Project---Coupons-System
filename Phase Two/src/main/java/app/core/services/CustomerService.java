package app.core.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

@Service
@Transactional
public class CustomerService extends ClientService {

	private static Long custId;

	public CustomerService() {

	}

	public CustomerService(CustomerRepository customerRepository, CouponRepository couponRepository) {
		super();
		this.customerRepository = customerRepository;
		this.couponRepository = couponRepository;
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Optional<Customer> opt = customerRepository.findByEmailAndPassword(email, password);
		if (opt.isPresent()) {
			Customer c = opt.get();
			custId = c.getId();
			return true;
		}
		return false;
	}

	/**
	 * This method gives accessibility to the logged-in Customer to purchase a
	 * Coupon object from 'coupon' table in: 'coupons_springApp_db' Database. The
	 * customer will not be able to purchase the given Coupon Object if: 1) He/She
	 * already bought a similar coupon (by it's id parameter). 2) The given Coupon
	 * Object is out of stock (amount parameter <= 0). 3) The given Coupon object
	 * date is expired (Date endDate has passed). If this three restrictions is
	 * false, the logged-in customer will be able to purchase the given Coupon
	 * object. Also, The given Coupon object amount parameter will be decreased by
	 * one after every purchase.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		try {
			Date currDate = new Date();
			Customer customer = customerRepository.getById(custId);
			List<Coupon> customerCoupons = customer.getCoupons();
			Optional<Coupon> opt = couponRepository.findById(coupon.getId());
			if (opt.isPresent()) {
				Coupon coup = opt.get();
				if (customerCoupons.contains(coup)) {
					throw new CouponSystemException("Coupon purchase Denied!" + " You've already bought this Coupon.");
				}
				if (coup.getAmount() <= 0) {
					throw new CouponSystemException("Coupon purchase Denied!" + " This Coupon is out of stock.");
				}
				if (coup.getEndDate().before(currDate)) {
					throw new CouponSystemException("Coupon purchase Denied!" + " This Coupon date is expired.");
				}

				customer.addCoupon(coup);
				customerRepository.save(customer);
				int upAmount = coup.getAmount() - 1;
				coup.setAmount(upAmount);
				couponRepository.save(coup);
				System.out.println("Coupon purchase Approved!");
			} else {
				throw new CouponSystemException("Couldn't find any coupon by ID: " + coupon.getId());
			}

		} catch (Exception e) {
			throw new CouponSystemException("purchaseCoupon from CustomerService Failed", e);
		}
	}

	/**
	 * Gets the entire List of Coupon object/s of the Customer that is logged-in.
	 * 
	 * @return List
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCustomerCoupons() throws CouponSystemException {
		try {
			List<Coupon> customerCoupons = couponRepository.findByCustomersId(Sort.by(Sort.Direction.ASC, "id"),
					custId);
			return customerCoupons;
		} catch (Exception e) {
			throw new CouponSystemException("getCustomerCoupons from CustomerService Failed", e);
		}

	}

	/**
	 * Receives a Coupon id parameter and seeks for a Coupon object by it, from:
	 * 'coupon' table in: 'coupons_springApp_db' Database.
	 * 
	 * @param
	 * @return Coupon
	 * @throws CouponSystemException
	 */
	public Coupon getOneCoupon(Long id) throws CouponSystemException {

		try {
			Optional<Coupon> opt = couponRepository.findById(id);
			if (opt.isPresent()) {
				Coupon c = opt.get();
				return c;
			} else {
				throw new CouponSystemException("Couldn't find any coupon by ID: " + id);
			}
		} catch (Exception e) {
			throw new CouponSystemException("getOneCoupon from CustomerService Failed", e);
		}
	}

	/**
	 * Gets the current logged-in Customer purchased Coupon List. Returns a List of
	 * Coupon object/s that are holding the same Category value (by the given
	 * Category enum).
	 * 
	 * @param
	 * @return List
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCustomerCoupons(Category category) throws CouponSystemException {
		try {
			List<Coupon> customerCoupons = couponRepository.findByCustomersId(Sort.by(Sort.Direction.ASC, "id"),
					custId);
			List<Coupon> customerCouponsByCategory = new ArrayList<>();

			for (Coupon coup : customerCoupons) {
				if (coup.getCategory() == category) {
					customerCouponsByCategory.add(coup);
				}
			}
			return customerCouponsByCategory;
		} catch (Exception e) {
			throw new CouponSystemException("getCustomerCoupons By Category from CustomerService Failed", e);
		}
	}

	/**
	 * Gets the current logged-in Customer purchased Coupon List. Returns a List of
	 * Coupon object/s that are holding a lower 'price' value from the given
	 * 'maxPrice' parameter.
	 * 
	 * @param
	 * @return List
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCustomerCoupons(double maxPrice) throws CouponSystemException {
		try {
			List<Coupon> customerCoupons = couponRepository.findByCustomersId(Sort.by(Sort.Direction.ASC, "id"),
					custId);
			List<Coupon> customerCouponsByPrice = new ArrayList<>();

			for (Coupon coup : customerCoupons) {
				if (coup.getPrice() < maxPrice) {
					customerCouponsByPrice.add(coup);
				}
			}
			return customerCouponsByPrice;
		} catch (Exception e) {
			throw new CouponSystemException("getCustomerCoupons By maxPrice from CustomerService Failed", e);
		}
	}

	/**
	 * Gets the logged-in customer details (firstName, email, e.g).
	 * 
	 * @return Customer
	 * @throws CouponSystemException
	 */
	public Customer getCustomerDetails() throws CouponSystemException {
		try {
			Customer c = customerRepository.getById(custId);
			return c;
		} catch (Exception e) {
			throw new CouponSystemException("getCustomerDetails from CustomerService Failed", e);
		}

	}

}
