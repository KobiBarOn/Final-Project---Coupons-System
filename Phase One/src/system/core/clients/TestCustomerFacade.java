package system.core.clients;

import java.util.Collection;
import java.util.List;
import system.core.beans.Category;
import system.core.beans.Coupon;
import system.core.beans.Customer;
import system.core.daos.CouponsDAO;
import system.core.daos.CouponsDBDAO;
import system.core.exceptions.CouponSystemException;

public class TestCustomerFacade {

	public static void main(String[] args) throws CouponSystemException {
		
		CouponsDAO dao = new CouponsDBDAO();
		LoginManager lm = LoginManager.getInstance();
		
		// Test LoginManager
		if (lm.login("kobesh04@gmail.com", "adadadad", ClientType.CUSTOMER) != null) {
		
		CustomerFacade csf = (CustomerFacade) lm.login("kobesh04@gmail.com", "adadadad", ClientType.CUSTOMER);
		System.out.println("Customer is logged-in");
		
		// Test purchaseCoupon of CustomerFacade
//		Coupon c = dao.getOneCoupon(67);
//		if (c != null) {
//		csf.purchaseCoupon(c);
//		}
		
		// Test getCustomerCoupons of CustomerFacade
//		List<Coupon> custCoupons = csf.getCustomerCoupons();
//		print(custCoupons);
		
		// Test getCustomerCoupons By Category of CustomerFacade
//		List<Coupon> custCouponsByCategory = csf.getCustomerCoupons(Category.FOOD);
//		print(custCouponsByCategory);
		
		// Test getCustomerCoupons By MAX Price of CustomerFacade
//		List<Coupon> custCouponsByMaxPrice = csf.getCustomerCoupons(6);
//		print(custCouponsByMaxPrice);
		
		// Test getCustomerDetails of CustomerFacade
//		Customer cust = csf.getCustomerDetails();
//		System.out.println(cust);
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
