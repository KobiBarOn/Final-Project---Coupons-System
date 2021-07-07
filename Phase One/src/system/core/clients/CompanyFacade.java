package system.core.clients;

import java.util.ArrayList;

import system.core.beans.Category;
import system.core.beans.Company;
import system.core.beans.Coupon;
import system.core.beans.CouponWithCustId;
import system.core.exceptions.CouponSystemException;

public class CompanyFacade extends ClientFacade {

	private static int compId;

	public CompanyFacade() {

	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {

		if (companiesDAO.isCompanyExists(email, password)) {
			Company currComp = companiesDAO.getOneCompanyByEmail(email);
			compId = currComp.getCompId();
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Creates a Coupon object and insert it (with his values) into 'coupons' table
	 * in: 'coupons_project_db' Database. This method will work only if the company
	 * coupons Array contains the given title parameter is by the same Company (by
	 * the same compId parameter). For more info: view: hash code and equals
	 * override method in CouponsApp.src.system.core.Company.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void addCoupon(Coupon coupon) throws CouponSystemException {
		try {
			Coupon temp = new Coupon();
			ArrayList<Coupon> AllCoupons = couponsDAO.getAllCoupons();
			for (Coupon coup : AllCoupons) {
				if (!coup.equals(coupon) || coup.getCompId() != coupon.getCompId()) {
					temp = coupon;
					continue;
				} else {
					temp = null;
					System.out.println("Error: Your company already has a coupon with this Title");
					break;
				}
			}
			System.out.println("New coupon was added: " + temp);
			couponsDAO.addCoupon(temp);
		} catch (Exception e) {
			throw new CouponSystemException("addCoupon of CompanyFacade Failed", e);
		}
	}

	/**
	 * An access method to update values (e.g, catId) of a specific Coupon object.
	 * coupId and compId are NOT updatable parameters.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		try {
			Coupon temp = couponsDAO.getOneCoupon(coupon.getCoupId());
			temp.setCategory(coupon.getCategory());
			temp.setTitle(coupon.getTitle());
			temp.setDescription(coupon.getDescription());
			temp.setStartDate(coupon.getStartDate());
			temp.setEndDate(coupon.getEndDate());
			temp.setAmount(coupon.getAmount());
			temp.setPrice(coupon.getPrice());
			temp.setImage(coupon.getImage());
			couponsDAO.updateCoupon(temp);
			System.out.println("Coupon was updated successfully:");
			System.out.println(temp);
		} catch (Exception e) {
			System.out.println("Couldn't find any coupon by this company ID: " + coupon.getCompId());
			throw new CouponSystemException("updateCoupon of CompanyFacade Failed", e);
		}
	}

	/**
	 * Deletes a Coupon object by the given coupId parameter. Also, deletes all
	 * purchased records by this coupId.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void deleteCoupon(int coupId) throws CouponSystemException {
		try {
			ArrayList<CouponWithCustId> allCouponsPurchases = couponsDAO.getCouponCustIdByCompId(compId);
			for (CouponWithCustId coup : allCouponsPurchases) {
				couponsDAO.deleteCouponPurchase(coup.getCustId(), coupId);
			}
			couponsDAO.deleteCouponByCoupId(coupId);
			System.out.println("All: " + coupId + "(ID) coupon records were deleted!");

		} catch (Exception e) {
			throw new CouponSystemException("deleteCoupon of CompanyFacade Failed", e);
		}
	}

	/**
	 * Gets the entire ArrayList of Coupon object/s of the company that is
	 * logged-in.
	 * 
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	public ArrayList<Coupon> getCompanyCoupons() throws CouponSystemException {
		try {
			ArrayList<Coupon> companyCoupons = couponsDAO.getCouponsByCompId(compId);
			return companyCoupons;
		} catch (Exception e) {
			throw new CouponSystemException("getCompanyCoupons of CompanyFacade Failed", e);
		}
	}

	/**
	 * Gets the Company created Coupon type ArrayList of the current logged-in
	 * Company. Returns a Coupon type ArrayList that contains a Coupon object/s that
	 * are holding the same Category type (by the given Category enum).
	 * 
	 * @param category
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	public ArrayList<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {
		try {
			ArrayList<Coupon> companyCoupons = couponsDAO.getCouponsByCompId(compId);
			ArrayList<Coupon> companyCouponsByCategory = new ArrayList<>();

			for (Coupon coup : companyCoupons) {
				if (coup.getCategory() == category) {
					companyCouponsByCategory.add(coup);
				}
			}
			return companyCouponsByCategory;
		} catch (Exception e) {
			throw new CouponSystemException("getCompanyCoupons By Category of CompanyFacade Failed", e);
		}
	}

	/**
	 * Gets the created Coupon type ArrayList of the current logged-in Company.
	 * Returns a Coupon type ArrayList that contains a Coupon object/s that are
	 * holding a lower value of the 'price' parameter from the given 'maxPrice'
	 * parameter.
	 * 
	 * @param maxPrice
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException {
		try {
			ArrayList<Coupon> companyCoupons = couponsDAO.getCouponsByCompId(compId);
			ArrayList<Coupon> companyCouponsByPrice = new ArrayList<>();

			for (Coupon coup : companyCoupons) {
				if (coup.getPrice() < maxPrice) {
					companyCouponsByPrice.add(coup);
				}
			}
			return companyCouponsByPrice;
		} catch (Exception e) {
			throw new CouponSystemException("getCompanyCoupons By maxPrice of CompanyFacade Failed", e);
		}
	}

	/**
	 * Gets the logged-in company details (compName, compEmail, e.g).
	 * 
	 * @return Company
	 * @throws CouponSystemException
	 */
	public Company getCompanyDetails() throws CouponSystemException {
		try {
			Company comp = companiesDAO.getOneCompanyById(compId);
			return comp;
		} catch (Exception e) {
			throw new CouponSystemException("getCompanyDetails of CompanyFacade Failed", e);
		}

	}

}
