package system.core;

import system.core.exceptions.CouponSystemException;

public class Program {

	public static void main(String[] args) throws CouponSystemException {

		try {

			AppTest appTest = new AppTest();
			appTest.testAll();

		} catch (Exception e) {
			throw new CouponSystemException("Program Failed", e);
		}
	}
}
