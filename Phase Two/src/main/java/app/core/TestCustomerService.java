package app.core;

import java.util.Collection;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.CustomerService;

@SpringBootApplication
public class TestCustomerService {

	public static void main(String[] args) throws CouponSystemException {

		ConfigurableApplicationContext ctx = SpringApplication.run(TestCustomerService.class, args);

		LoginManager loginManager = ctx.getBean(LoginManager.class);

		// Test LoginManager
		CustomerService custS = (CustomerService) loginManager.login(
				"adam@gmail.com", "adadadad", ClientType.CUSTOMER);
		if (custS != null) {

		// Test purchaseCoupon from CustomerService
//		Coupon coup1 = custS.getOneCoupon(9L);
//		System.out.println("======================");
//		System.out.println("Examining your Coupon purchase request: ");
//		System.out.println(coup1);
//		System.out.println("======================");
//		custS.purchaseCoupon(coup1);
			
		// Test getCustomerCoupons from CustomerService
//		List<Coupon> custCoupons = custS.getCustomerCoupons();
//		System.out.println("=========================");
//		System.out.println("Your Coupon purchase history:");
//		print(custCoupons);
			
		// Test getOneCoupon from CustomerService
//		Coupon coup2 = custS.getOneCoupon(10L);
//		System.out.println("=========================");
//		System.out.println("Coupon info:");
//		System.out.println(coup2);
//		System.out.println("=========================");

		// Test getCustomerCoupons By Category from CustomerService
//		Category category = Category.ELECTRONICS;
//		List<Coupon> custCouponsByCategory = custS.getCustomerCoupons(category);
//		System.out.println("=========================");
//		System.out.println("Your Coupons By Category: " + category);
//		print(custCouponsByCategory);

		// Test getCustomerCoupons By MAX Price from CustomerService
//		double maxPrice = 500;
//		List<Coupon> custCouponsByMaxPrice = custS.getCustomerCoupons(maxPrice);
//		System.out.println("=========================");
//		System.out.println("Your Coupons By MAX Price: " + maxPrice);
//		print(custCouponsByMaxPrice);

		// Test getCustomerDetails from CustomerService
//		Customer cust = custS.getCustomerDetails();
//		System.out.println("=========================");
//		System.out.println("Your Details:");
//		System.out.println(cust);
//		System.out.println("=========================");
			
		}
		
		System.out.println("Closing Application...");
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ctx.close();
	}

	public static void print(Collection<?> col) {
		System.out.println("=========================");
		if (!col.isEmpty()) {
			for (Object e : col) {
				System.out.println(e);
			}
		} else {
			System.out.println("This Collection is Empty!");
		}
		System.out.println("=========================");
	}
}