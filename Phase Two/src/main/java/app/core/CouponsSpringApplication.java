package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import app.core.exceptions.CouponSystemException;

@SpringBootApplication
public class CouponsSpringApplication {

	public static void main(String[] args) throws CouponSystemException {

		ConfigurableApplicationContext ctx = SpringApplication.run(CouponsSpringApplication.class, args);

		try {

			Test test = ctx.getBean(Test.class);
			test.testAll();

		} catch (Exception e) {
			throw new CouponSystemException("CouponSpringApplication Failed", e);
		}

		System.out.println("Closing Application...");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ctx.close();

	}

}
