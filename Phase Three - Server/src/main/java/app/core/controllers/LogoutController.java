package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import app.core.entities.LoginItem;
import app.core.session.Session;
import app.core.session.SessionContext;

@CrossOrigin
@RestController
public class LogoutController {

	@Autowired
	private SessionContext sessionContext;

	@PostMapping(path = "/logout")
	public ResponseEntity<?> logout(@RequestHeader String token) {
		try {
			Session session = this.sessionContext.getSession(token);
			if (session != null) {
				this.sessionContext.invalidate(session);
				System.out.println("A Client is Logged-out");
				return ResponseEntity.status(HttpStatus.OK).body(new LoginItem(session.token));
			}
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					"You are not Logged-in OR you are passing the wrong Token. There is no Session");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

}
