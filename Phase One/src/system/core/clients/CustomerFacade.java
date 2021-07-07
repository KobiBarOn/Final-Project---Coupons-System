package system.core.clients;

import java.util.ArrayList;
import java.util.Date;
import system.core.beans.Category;
import system.core.beans.Coupon;
import system.core.beans.Customer;
import system.core.exceptions.CouponSystemException;

public class CustomerFacade extends ClientFacade {

	private static int custId;

	public CustomerFacade() {

	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {

		if (customersDAO.isCustomerExists(email, password)) {
			Customer currCust = customersDAO.getOneCustomerByEmail(email);
			custId = currCust.getCustId();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method gives the logged-in customer accessibility to purchase a Coupon
	 * Object from 'coupons' table in: 'coupons_project_db' Database. The customer
	 * will not be able to purchase the given Coupon Object if: 1) If he/she already
	 * bought a similar coupon (by it's coupoId). 2) The given Coupon Object is out
	 * of stock (int amount <= 0). 3) The given Coupon Object is expired (Date
	 * endDate has passed). If this three restrictions is false the logged-in
	 * customer will be able to purchase the given Coupon Object. Also, The given
	 * Coupon Object amount parameter will be increased by one after any coupon
	 * purchase.
	 * 
	 * @param coupon
	 * @throws CouponSystemException
	 */
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
		try {
			Date currDate = new Date();
			Coupon coup = couponsDAO.getOneCoupon(coupon.getCoupId());
			ArrayList<Coupon> custCoupons = couponsDAO.getCouponsByCustId(custId);
			if (coup != null) {
				if (custCoupons.contains(coup)) {
					System.out.println("Sorry, but you can't purchase this coupon.");
					throw new CouponSystemException("You've already bought this coupon.");
				}
				if (coupon.getAmount() <= 0) {
					System.out.println("Sorry, but you can't purchase this coupon.");
					throw new CouponSystemException("This coupon is out of stock.");
				}
				if (coupon.getEndDate().before(currDate)) {
					System.out.println("Sorry, but you can't purchase this coupon.");
					throw new CouponSystemException("This coupon date is expired.");
				}

				couponsDAO.addCouponPurchase(custId, coupon.getCoupId());
				System.out.println("You've successfully purchased coupon: " + coupon);
				int upAmount = coupon.getAmount() - 1;
				coupon.setAmount(upAmount);
				couponsDAO.updateCoupon(coupon);
			}

		} catch (Exception e) {
			throw new CouponSystemException("purchaseCoupon Failed", e);
		}
	}

	/**
	 * Gets the entire ArrayList of Coupon object/s of the customer that is
	 * logged-in.
	 * 
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	public ArrayList<Coupon> getCustomerCoupons() throws CouponSystemException {
		try {
			ArrayList<Coupon> customerCoupons = couponsDAO.getCouponsByCustId(custId);
			return customerCoupons;
		} catch (Exception e) {
			throw new CouponSystemException("getCustomerCoupons of CustomerFacade Failed", e);
		}

	}

	/**
	 * Gets the Customer purchased Coupon type ArrayList of the current logged-in
	 * Customer. Returns a Coupon type ArrayList that contains a Coupon object/s
	 * that are holding the same Category type (by the given Category enum).
	 * 
	 * @param category
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	public ArrayList<Coupon> getCustomerCoupons(Category category) throws CouponSystemException {
		try {
			ArrayList<Coupon> customerCoupons = couponsDAO.getCouponsByCustId(custId);
			ArrayList<Coupon> customerCouponsByCategory = new ArrayList<>();

			for (Coupon coup : customerCoupons) {
				if (coup.getCategory() == category) {
					customerCouponsByCategory.add(coup);
				}
			}
			return customerCouponsByCategory;
		} catch (Exception e) {
			throw new CouponSystemException("getCustomerCoupons By Category of CustomerFacade Failed", e);
		}
	}

	/**
	 * Gets the created Coupon type ArrayList of the current logged-in Customer.
	 * Returns a Coupon type ArrayList that contains a Coupon object/s that are
	 * holding a lower value of the 'price' parameter from the given 'maxPrice'
	 * parameter.
	 * 
	 * @param maxPrice
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws CouponSystemException {
		try {
			ArrayList<Coupon> customerCoupons = couponsDAO.getCouponsByCustId(custId);
			ArrayList<Coupon> customerCouponsByPrice = new ArrayList<>();

			for (Coupon coup : customerCoupons) {
				if (coup.getPrice() < maxPrice) {
					customerCouponsByPrice.add(coup);
				}
			}
			return customerCouponsByPrice;
		} catch (Exception e) {
			throw new CouponSystemException("getCustomerCoupons By maxPrice of CustomerFacade Failed", e);
		}
	}

	/**
	 * Gets the logged-in customer details (firstName, custEmail, e.g).
	 * 
	 * @return Customer
	 * @throws CouponSystemException
	 */
	public Customer getCustomerDetails() throws CouponSystemException {
		try {
			Customer cust = customersDAO.getOneCustomerById(custId);
			return cust;
		} catch (Exception e) {
			throw new CouponSystemException("getCustomerDetails of CustomerFacade Failed", e);
		}

	}

}
