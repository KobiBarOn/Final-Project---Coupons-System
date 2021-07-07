package system.core.clients;

import system.core.exceptions.CouponSystemException;

public class TestLoginManager {

	public static void main(String[] args) throws CouponSystemException {

		LoginManager lm = LoginManager.getInstance();

//		lm.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

//		lm.login("support@google1011.com", "apapapa", ClientType.COMPANY);

		lm.login("kobesh04@gmail.com", "adadadad", ClientType.CUSTOMER);
	}

}
