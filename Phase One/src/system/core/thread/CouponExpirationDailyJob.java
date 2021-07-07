package system.core.thread;

import java.util.ArrayList;
import java.util.Date;
import system.core.beans.Coupon;
import system.core.clients.CompanyFacade;
import system.core.daos.CouponsDAO;
import system.core.daos.CouponsDBDAO;
import system.core.exceptions.CouponSystemException;

/**
 * This Runnable class runs once in a day since you call the 'start' method and
 * it end when you call the 'stop' method. This class seeks for an expiration
 * date (Date endDate) of a Coupon object/s and if it finds one or more, it/they
 * will be deleted from the 'coupon_project_db' database and it's/their entire
 * purchase history as well.
 * 
 * @author Kobi Bar-on
 *
 */
public class CouponExpirationDailyJob implements Runnable {

	public CompanyFacade cof = new CompanyFacade();
	public CouponsDAO dao = new CouponsDBDAO();
	public boolean quit;
	public Date currDate = new Date();
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
	public void run() {
		System.out.println("Looking for expired coupons for deletion");
		while (!quit) {
			try {
				ArrayList<Coupon> allCoupons = dao.getAllCoupons();
				for (Coupon coup : allCoupons) {
					if (coup.getEndDate().before(currDate)) {
						cof.deleteCoupon(coup.getCoupId());
					}
				}

			} catch (CouponSystemException e) {
				System.out.println("CouponExpirationDailyJob Failed");
				System.out.println(e);
			}
		}
		try {
			Thread.sleep(1000 * 60 * 60 * 24);
		} catch (InterruptedException e) {
			System.out.println("Daily Job Timing Failed (Sleep method Failed)");
		}
	}

	public void stop() {
		setQuit(true);
		dailyJobThread.interrupt();
		System.out.println("Daily Job is finished");
	}

}
