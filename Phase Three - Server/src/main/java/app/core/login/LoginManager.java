package app.core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.ClientService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;

@Component
public class LoginManager {

	@Autowired
	private ApplicationContext ctx;

	private LoginManager() {

	}

	/**
	 * Checks the given parameters in front of the 'coupons_spring_rest_app_db' database
	 * and if the values are correct (the values are registered in this database)
	 * then it gives the client access to the appropriate Service (returns the
	 * appropriate type of ClientService by the ApplicationContext).
	 * 
	 * @param email, password, clientType
	 * @return AdminService/CompanyService/CustomerService
	 * @throws CouponSystemException
	 */
	public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
		try {

			if (clientType == ClientType.ADMINISTRATOR) {
				AdminService adS = ctx.getBean(AdminService.class);
				if (adS.login(email, password)) {
					System.out.println("An Administrator is Logged-in");
					return adS;
				}
			}
			if (clientType == ClientType.COMPANY) {
				CompanyService compS = ctx.getBean(CompanyService.class);
				if (compS.login(email, password)) {
					System.out.println("A Company is Logged-in");
					return compS;
				}
			}
			if (clientType == ClientType.CUSTOMER) {
				CustomerService custS = ctx.getBean(CustomerService.class);
				if (custS.login(email, password)) {
					System.out.println("A Customer is Logged-in");
					return custS;
				}
			}
			
			throw new CouponSystemException("Login Failed! Email/Password is incorrect, Please try again."
					+ " Also, make sure you've entered the appropriate ''Client type''");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CouponSystemException(e.getMessage());
		}
	}
}
