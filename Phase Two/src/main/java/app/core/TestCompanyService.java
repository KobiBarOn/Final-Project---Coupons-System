package app.core;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.CompanyService;

@SpringBootApplication
public class TestCompanyService {

	public static void main(String[] args) throws CouponSystemException {

		ConfigurableApplicationContext ctx = SpringApplication.run(TestCompanyService.class, args);

		LoginManager loginManager = ctx.getBean(LoginManager.class);

		// Test LoginManager
		CompanyService compS = (CompanyService) loginManager.login(
				"support@space-gym.com", "spspspsp", ClientType.COMPANY);
		if (compS != null) {

			final Company loggedInCompany = compS.getCompanyDetails();

		// Test addCoupon from CompanyService
//		LocalDate startDate1 = LocalDate.of(2021, 3, 20);
//		Date startSqlDate1 = Date.valueOf(startDate1);
//
//		LocalDate endDate1 = LocalDate.of(2021, 6, 4);
//		Date endSqlDate1 = Date.valueOf(endDate1);
//
//		Coupon coup1 = new Coupon(Category.GYM, "Yoga", "2 Yoga lessons in a price of 1", startSqlDate1, endSqlDate1, 350, 50,
//				"2 Yoga lessons in 50₪ ONLY", loggedInCompany);
//		compS.addCoupon(coup1);

		// Test updateCoupon from CompanyService
//		LocalDate startDate2 = LocalDate.of(2021, 3, 9);
//		Date startSqlDate2 = Date.valueOf(startDate2);
//
//		LocalDate endDate2 = LocalDate.of(2021, 5, 2);
//		Date endSqlDate2 = Date.valueOf(endDate2);
//		
//		Coupon coup2 = compS.getOneCoupon(11L);
//		coup2.setCategory(Category.ELECTRONICS);
//		coup2.setTitle("Yoga1111");
//		coup2.setDescription("2 Yoga lessons in a price of 1111111");
//		coup2.setStartDate(startSqlDate2);
//		coup2.setEndDate(endSqlDate2);
//		coup2.setAmount(900);
//		coup2.setPrice(50);
//		coup2.setImage("2 Yoga lessons in 50₪ ONLY1111");
//		// WILL NOT WORK - throws CouponSystemException of: "not owned Coupon"/"Couldn't find Coupon".
////		coup2.setId(4L);
//		// WILL WORK (will update all other parameters) BUT WILL NOT update the owning Company!
////		Company comp = new Company("Example Company", "example@mail.com", "1234");
////		coup2.setCompany(comp);
//		// SEND THE UPDATED COUPON BACK TO THE DB
//		compS.updateCoupon(coup2);

		// Test deleteCoupon from CompanyService
//		compS.deleteCoupon(3L);

//		// Test getOneCoupon from CompanyService
//		Coupon coup3 = compS.getOneCoupon(11L);
//		System.out.println("=========================");
//		System.out.println("Coupon info:");
//		System.out.println(coup3);
//		System.out.println("=========================");

		// Test getCompanyCoupons from CompanyService
//		List<Coupon> compCoupons = compS.getCompanyCoupons();
//		System.out.println("=========================");
//		System.out.println("All your Company Coupons:");
//		print(compCoupons);

		// Test getCompanyCoupons By Category from CompanyService
//		Category category = Category.ELECTRONICS;
//		List<Coupon> compCouponsByCategory = compS.getCompanyCoupons(category);
//		System.out.println("=========================");
//		System.out.println("Your Company Coupons By Category: " + category);
//		print(compCouponsByCategory);

		// Test getCompanyCoupons By MAX Price from CompanyService
//		double maxPrice = 20;
//		List<Coupon> compCouponsByMaxPrice = compS.getCompanyCoupons(maxPrice);
//		System.out.println("=========================");
//		System.out.println("Your Company Coupons By MAX Price: " + maxPrice);
//		print(compCouponsByMaxPrice);

		// Test getCompanyDetails from CompanyService
//		Company comp = compS.getCompanyDetails();
//		System.out.println("=========================");
//		System.out.println("Your Company Details:");
//		System.out.println(comp);
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
