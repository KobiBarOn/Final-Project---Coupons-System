package app.core.job;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.core.entities.Coupon;
import app.core.repositories.CouponRepository;

/**
 * This Runnable class runs once in a day since you call the 'start' method and
 * it ends when you call the 'stop' method. This class seeks for an expiration
 * date (Date endDate) of a Coupon Object/s and if it finds one or more, it/they
 * will be deleted from the 'coupons_springApp_db' database and it's/their
 * entire purchase history as well.
 * 
 * @author Kobi Bar-on
 *
 */
@Component
public class CouponExpirationDailyJob implements Runnable {

	@Autowired
	private CouponRepository couponRepository;
	private boolean quit;
	private Thread dailyJobThread;

	public CouponExpirationDailyJob() {
		super();
		dailyJobThread = new Thread(this, "job");
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	@Override
	@Transactional
	public void run() {
		while (!quit) {
			System.out.println("Looking for expired Coupons for deletion...");
			try {
				List<Coupon> expiredCoupons = couponRepository.findByEndDateBefore(Date.valueOf(LocalDate.now()));
				synchronized (expiredCoupons) {

					if (expiredCoupons != null && !expiredCoupons.isEmpty()) {

						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						couponRepository.deleteInBatch(expiredCoupons);
						System.out.println("All expired Coupons were deleted");
					} else {
						System.out.println("No expired Coupons for deletion.");
					}
				}

			} catch (Exception e) {
				System.out.println("CouponExpirationDailyJob Failed\n" + e);
			}

			try {
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				System.out.println("The Daily Job is Over.");
			}
		}

	}

	@PostConstruct
	public void initDailyJob() {
		System.out.println("Daily job Starts...");
		dailyJobThread.start();
	}

	@PreDestroy
	public void destroyDailyJob() {
		setQuit(true);
		dailyJobThread.interrupt();
	}

}
