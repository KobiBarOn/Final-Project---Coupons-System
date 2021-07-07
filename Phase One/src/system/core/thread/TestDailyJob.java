package system.core.thread;

public class TestDailyJob {

	public static void main(String[] args) {

		CouponExpirationDailyJob r = new CouponExpirationDailyJob();
		Thread t = new Thread(r, "t");

		t.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		r.stop();
	}
}
