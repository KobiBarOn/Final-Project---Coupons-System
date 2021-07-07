package system.core.clients;

import system.core.exceptions.CouponSystemException;

public class LoginManager {

	private static LoginManager instance = new LoginManager();

	private LoginManager() {

	}

	public static LoginManager getInstance() {
		return instance;
	}
	
	

	/**
	 * Checks the given parameters in front of the 'coupons_project_db' database and
	 * if the values are correct (the values are registered in this database) then
	 * it gives the client access to it and returns the appropriate type of
	 * ClientFacade.
	 * 
	 * @param email, password, clientType
	 * @return AdminFacade/CompanyFacade/CustomerFacade
	 * @throws CouponSystemException
	 */
	public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException {
		try {

			if (clientType == ClientType.ADMINISTRATOR) {
				AdminFacade adf = new AdminFacade();
				if (adf.login(email, password)) {
					return adf;
				}
			} 
			if (clientType == ClientType.COMPANY) {
				CompanyFacade compf = new CompanyFacade();
				if (compf.login(email, password)) {
					return compf;
				}
			} 
			if (clientType == ClientType.CUSTOMER) {
				CustomerFacade custf = new CustomerFacade();
				if (custf.login(email, password)) {
					return custf;
				}
			} 

			System.out.println("Login Failed! Email/Password is incorrect, Please try again.");
			System.out.println("Also make sure you have entered the appropriate ''Client type''");
			return null;
			
		} catch (Exception e) {
			throw new CouponSystemException("LoginManager Failed", e);
		}
	}
}
