package system.core.clients;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import system.core.beans.Category;
import system.core.beans.Company;
import system.core.beans.Coupon;
import system.core.exceptions.CouponSystemException;

public class TestCompanyFacade {

	public static void main(String[] args) throws CouponSystemException {

		LoginManager lm = LoginManager.getInstance();

		// Test LoginManager
		if (lm.login("support@google1011.com", "apapapa", ClientType.COMPANY) != null) {
			
		CompanyFacade cof = (CompanyFacade) lm.login("support@google1011.com", "apapapa", ClientType.COMPANY);
		System.out.println("Company is logged-in");
			
		// Test addCoupon of CompanyFacade
//		LocalDate startDate = LocalDate.of(2020, 12, 28);
//		Date startSqlDate = Date.valueOf(startDate);
//
//		LocalDate endDate = LocalDate.of(2021, 2, 2);
//		Date endSqlDate = Date.valueOf(endDate);

//		Coupon c1 = new Coupon(0, 30, Category.FOOD, "Cow3", "chops", startSqlDate, endSqlDate, 12, 10.99, "nice chops");
//		cof.addCoupon(c1);

		// Test updateCoupon of CompanyFacade
//		Coupon c2 = new Coupon(43, 30, Category.GYM, "Kebab", "cchops", startSqlDate, endSqlDate, 12, 5.5, "nice nice");
//		cof.updateCoupon(c2);

		// Test deleteCoupon of CompanyFacade
//		cof.deleteCoupon(61);

		// Test getCompanyCoupons of CompanyFacade
//		List<Coupon> compCoupons = cof.getCompanyCoupons();
//		print(compCoupons);

		// Test getCompanyCoupons By Category of CompanyFacade
//		List<Coupon> compCouponsByCategory = cof.getCompanyCoupons(Category.FOOD);
//		print(compCouponsByCategory);

		// האם זו הדרישה בחוברת הנחיות?!!?!?
		// Test getCompanyCoupons By MAX Price of CompanyFacade
//		List<Coupon> compCouponsByMaxPrice = cof.getCompanyCoupons(6);
//		print(compCouponsByMaxPrice);

		// Test getCompanyDetails of CompanyFacade
//		Company comp = cof.getCompanyDetails();
//		System.out.println(comp);
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
