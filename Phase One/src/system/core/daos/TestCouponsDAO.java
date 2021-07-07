package system.core.daos;

import java.sql.Date;
import java.time.LocalDate;
import system.core.beans.Category;
import system.core.beans.Coupon;
import system.core.exceptions.CouponSystemException;

public class TestCouponsDAO {

	public static void main(String[] args) throws CouponSystemException {

		CouponsDAO dao = new CouponsDBDAO();

		// Test addCoupon
//		LocalDate startDate1 = LocalDate.of(2020, 11, 20);
//		Date startSqlDate1 = Date.valueOf(startDate1);

//		LocalDate endDate1 = LocalDate.of(2020, 12, 9);
//		Date endSqlDate1 = Date.valueOf(endDate1);

//		Coupon c = new Coupon(0, 30, Category.FOOD, "Beacon", "chops", startSqlDate1, endSqlDate1, 12, 5.5, "nice beacon");
//		dao.addCoupon(c);

		// Test updateCoupon
//		LocalDate startDate2 = LocalDate.of(2020, 12, 4);
//		Date startSqlDate2 = Date.valueOf(startDate2);

//		LocalDate endDate2 = LocalDate.of(2020, 1, 20);
//		Date endSqlDate2 = Date.valueOf(endDate2);

//		Coupon coup = new Coupon(14, 1, Category.ELECTRICITY, "Laptop", "dell", startSqlDate2, endSqlDate2, 5, 1150.99, "blabla");
//		dao.updateCoupon(coup);

		// Test deleteCouponByCoupId
//		dao.deleteCouponByCoupId(16);

		// Test deleteCouponByCompId
//		dao.deleteCouponByCompId(10);

		// Test getAllCoupons
//		dao.getAllCoupons();

		// Test getOneCouponByCoupId
//		dao.getOneCoupon(15);

		// Test getCouponByCompId
//		dao.getCouponsByCompId(1);

		// Test getCouponByCustId
//		dao.getCouponsByCustId(3);

		// Test addCouponPurchase
//		dao.addCouponPurchase(4, 61);

		// Test deleteCouponPurchase
//		dao.deleteCouponPurchase(1, 16);

	}
}
