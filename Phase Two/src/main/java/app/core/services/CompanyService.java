package app.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;

@Service
@Transactional
public class CompanyService extends ClientService {

	private static Long compId;

	public CompanyService() {

	}

	public CompanyService(CompanyRepository companyRepository, CouponRepository couponRepository) {
		super();
		this.companyRepository = companyRepository;
		this.couponRepository = couponRepository;
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Optional<Company> opt = companyRepository.findByEmailAndPassword(email, password);
		if (opt.isPresent()) {
			Company c = opt.get();
			compId = c.getId();
			return true;
		}
		return false;
	}

	/**
	 * Creates a Coupon object and insert it (with his values) into 'coupons' table
	 * in: 'coupons_springApp_db' Database. This method will work only if the List
	 * of Coupons of the logged-in Company doesn't contains another coupon that
	 * holds the same 'title' parameter value.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void addCoupon(Coupon coupon) throws CouponSystemException {

		try {
			Company comp = companyRepository.getById(compId);
			List<Coupon> companyCoupons = comp.getCoupons();
			Coupon coupFormDb = couponRepository.findByTitleIgnoreCase(coupon.getTitle());
			if (companyCoupons.contains(coupFormDb)) {
				throw new CouponSystemException(
						"Your company already has a coupon with this title: " + coupon.getTitle());
			} else {
				comp.addCoupon(coupon);
				companyRepository.save(comp);
				System.out.println("'" + comp.getName() + "' added new coupon");
			}

		} catch (Exception e) {
			throw new CouponSystemException("addCoupon from CompanyService Failed", e);
		}
	}

	/**
	 * An access method to update values (e.g, category, title) of a specific Coupon
	 * object. The Coupon id parameter and the owning Company object are NOT
	 * updatable/changeable. The logged-in Company can only update her own Coupon
	 * objects!
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void updateCoupon(Coupon coupon) throws CouponSystemException {

		try {
			List<Coupon> companyCoupons = couponRepository.findByCompanyId(null, compId);
			Optional<Coupon> opt = couponRepository.findById(coupon.getId());
			if (opt.isPresent()) {
				Coupon coup = opt.get();
				if (companyCoupons.contains(coup)) {
					coup.setCategory(coupon.getCategory());
					coup.setTitle(coupon.getTitle());
					coup.setDescription(coupon.getDescription());
					coup.setStartDate(coupon.getStartDate());
					coup.setEndDate(coupon.getEndDate());
					coup.setAmount(coupon.getAmount());
					coup.setPrice(coupon.getPrice());
					coup.setImage(coupon.getImage());
//					if (coupon.getCompany() != coup.getCompany()) {
//						throw new CouponSystemException(
//								"Sorry, but you can't update the owning Company for Coupons.");
//					}
					couponRepository.save(coup);
					System.out.println("Coupon was updated successfully:");
					System.out.println(coup);
				} else {
					throw new CouponSystemException(
							"Sorry, but you can't update a Coupon that doesn't belong to your Company.");
				}
			} else {
				throw new CouponSystemException("Couldn't find any Coupon by this ID: " + coupon.getId());
			}
		} catch (Exception e) {
			throw new CouponSystemException("updateCoupon from CompanyService Failed", e);
		}
	}

	/**
	 * Deletes a Coupon object by the given Coupon id parameter. Also, deletes all
	 * purchased records of this Coupon id. The logged-in Company can only delete
	 * her own Coupon objects.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	public void deleteCoupon(Long id) throws CouponSystemException {

		try {
			List<Coupon> companyCoupons = couponRepository.findByCompanyId(null, compId);
			Optional<Coupon> opt = couponRepository.findById(id);
			if (opt.isPresent()) {
				Coupon coup = opt.get();
				if (companyCoupons.contains(coup)) {
					companyCoupons.remove(coup);
					couponRepository.deleteById(id);
					System.out.println("All: ''" + coup.getTitle() + "'' Coupon records were deleted!");
				} else {
					throw new CouponSystemException(
							"Sorry, but you can't delete a Coupon that doesn't belong to your Company.");
				}
			} else {
				throw new CouponSystemException("Couldn't find any Coupon by this ID: " + id);
			}

		} catch (Exception e) {
			throw new CouponSystemException("deleteCoupon from CompanyService Failed", e);
		}
	}

	/**
	 * Gets the entire List of Coupon object/s of the Company that is logged-in.
	 * 
	 * @return List
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCompanyCoupons() throws CouponSystemException {

		try {
			List<Coupon> companyCoupons = couponRepository.findByCompanyId(Sort.by(Sort.Direction.ASC, "id"), compId);
			return companyCoupons;

		} catch (Exception e) {
			throw new CouponSystemException("getCompanyCoupons from CompanyService Failed", e);
		}
	}

	/**
	 * Receives an id parameter of a Coupon object and returns it if it belongs to
	 * the Company that is logged-in.
	 * 
	 * @param
	 * @return Coupon
	 * @throws CouponSystemException
	 */
	public Coupon getOneCoupon(Long id) throws CouponSystemException {

		try {
			List<Coupon> companyCoupons = couponRepository.findByCompanyId(null, compId);
			Optional<Coupon> opt = couponRepository.findById(id);
			if (opt.isPresent()) {
				Coupon coup = opt.get();
				if (companyCoupons.contains(coup)) {
					return coup;
				} else {
					throw new CouponSystemException("This data cannot be displayed/edited by you. "
							+ "This Coupon doesn't belong to your Company.");
				}
			} else {
				throw new CouponSystemException("Couldn't find any coupon by ID: " + id);
			}
		} catch (Exception e) {
			throw new CouponSystemException("getOneCoupon from CompanyService Failed", e);
		}
	}

	/**
	 * Gets the current logged-in Company purchased Coupon List. Returns a List of
	 * Coupon object/s that are holding the same Category value (by the given
	 * Category enum).
	 * 
	 * @param
	 * @return List
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCompanyCoupons(Category category) throws CouponSystemException {

		try {
			List<Coupon> companyCoupons = couponRepository.findByCompanyId(Sort.by(Sort.Direction.ASC, "id"), compId);
			List<Coupon> companyCouponsByCategory = new ArrayList<>();

			for (Coupon coup : companyCoupons) {
				if (coup.getCategory() == category) {
					companyCouponsByCategory.add(coup);
				}
			}
			return companyCouponsByCategory;
		} catch (Exception e) {
			throw new CouponSystemException("getCompanyCoupons By Category from CompanyService Failed", e);
		}
	}

	/**
	 * Gets the current logged-in Company purchased Coupon List. Returns a List of
	 * Coupon object/s that are holding a lower 'price' value from the given
	 * 'maxPrice' parameter.
	 * 
	 * @param
	 * @return List
	 * @throws CouponSystemException
	 */
	public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponSystemException {

		try {
			List<Coupon> companyCoupons = couponRepository.findByCompanyId(Sort.by(Sort.Direction.ASC, "id"), compId);
			List<Coupon> companyCouponsByMaxPrice = new ArrayList<>();

			for (Coupon coup : companyCoupons) {
				if (coup.getPrice() < maxPrice) {
					companyCouponsByMaxPrice.add(coup);
				}
			}
			return companyCouponsByMaxPrice;
		} catch (Exception e) {
			throw new CouponSystemException("getCompanyCoupons By maxPrice from CompanyService Failed", e);
		}
	}

	/**
	 * Gets the logged-in company details (name, email, e.g).
	 * 
	 * @return Company
	 * @throws CouponSystemException
	 */
	public Company getCompanyDetails() throws CouponSystemException {

		try {
			Company c = companyRepository.getById(compId);
			return c;
		} catch (Exception e) {
			throw new CouponSystemException("getCompanyDetails from CompanyService Failed", e);
		}

	}

}
