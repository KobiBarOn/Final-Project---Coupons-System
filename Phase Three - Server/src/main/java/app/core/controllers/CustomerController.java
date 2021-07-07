package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import app.core.entities.Category;
import app.core.exceptions.CouponSystemException;
import app.core.services.CustomerService;
import app.core.session.Session;
import app.core.session.SessionContext;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/customers")
public class CustomerController {

	@Autowired
	private SessionContext sessionContext;

	public CustomerController() {

	}

	private CustomerService isCustomerLoggedIn(String token) {
		try {
			Session session = this.sessionContext.getSession(token);
			if (session != null) {
				CustomerService customerService = (CustomerService) session.getAttribute("CustomerService");
				return customerService;
			}
			throw new CouponSystemException("There is no Session. You are NOT Logged-in!");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					"You must be Logged-in as ''CUSTOMER'' in order to be able to use Customers API."
							+ "You need to recieve a Token in order to use this API Controller");
		}
	}

	@GetMapping(path = "/coupon/all-coupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader String token) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCustomerLoggedIn(token).getAllCoupons());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/coupon/purchase")
	public ResponseEntity<?> purchaseCoupon(@RequestHeader String token, @RequestParam Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCustomerLoggedIn(token).purchaseCoupon(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/customer/all-coupon-purchases")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader String token) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCustomerLoggedIn(token).getCustomerCoupons());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/coupon/single-coupon")
	public ResponseEntity<?> getOneCoupon(@RequestHeader String token, @RequestParam Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCustomerLoggedIn(token).getOneCoupon(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/customer/all-coupon-purchases-by-category")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader String token, @RequestParam Category category) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCustomerLoggedIn(token).getCustomerCoupons(category));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/customer/all-coupon-purchases-by-MAX-price")
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader String token, @RequestParam double maxPrice) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCustomerLoggedIn(token).getCustomerCoupons(maxPrice));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/logged-in-customer/details")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader String token) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCustomerLoggedIn(token).getCustomerDetails());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
