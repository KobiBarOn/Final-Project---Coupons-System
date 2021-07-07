package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import app.core.entities.LoginItem;
import app.core.exceptions.CouponSystemException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.AdminService;
import app.core.services.ClientService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import app.core.session.Session;
import app.core.session.SessionContext;

@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	private SessionContext sessionContext;
	@Autowired
	private LoginManager loginManager;

	@PostMapping("/login")
	public LoginItem login(@RequestParam String email, @RequestParam String password,
			@RequestParam ClientType clientType) {
		
		try {

			ClientService service = loginManager.login(email, password, clientType);
			if (service instanceof AdminService) {
				Session session = sessionContext.createSession();
				session.setAttribute("AdminService", service);
				return new LoginItem(session.token);
			}
			if (service instanceof CompanyService) {
				Session session = sessionContext.createSession();
				session.setAttribute("CompanyService", service);
				return new LoginItem(session.token);
			}
			if (service instanceof CustomerService) {
				Session session = sessionContext.createSession();
				session.setAttribute("CustomerService", service);
				return new LoginItem(session.token);
			}

			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
					"Login Failed! Email/Password is incorrect, Please try again."
							+ " Also, make sure you've entered the appropriate ''Client type''");

		} catch (CouponSystemException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}

	}

}
