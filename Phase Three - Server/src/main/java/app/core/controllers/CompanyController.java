package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.services.CompanyService;
import app.core.session.Session;
import app.core.session.SessionContext;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/companies")
public class CompanyController {

	@Autowired
	private SessionContext sessionContext;

	public CompanyController() {

	}

	private CompanyService isCompanyLoggedIn(String token) {
		try {
			Session session = this.sessionContext.getSession(token);
			if (session != null) {
				CompanyService companyService = (CompanyService) session.getAttribute("CompanyService");
				return companyService;
			}
			throw new CouponSystemException("There is no Session. You are NOT Logged-in!");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					"You must be Logged-in as ''COMPANY'' in order to be able to use Companies API."
							+ "You need to recieve a Token in order to use this API Controller");
		}
	}

	@PostMapping(path = "/coupon/add")
	public ResponseEntity<?> addCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {
		try {
			final Company loggedInCompany = isCompanyLoggedIn(token).getCompanyDetails();
			coupon.setCompany(loggedInCompany);
			return ResponseEntity.status(HttpStatus.OK).body(isCompanyLoggedIn(token).addCoupon(coupon));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping(path = "/coupon/update")
	public ResponseEntity<?> updateCoupon(@RequestHeader String token, @RequestBody Coupon coupon) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCompanyLoggedIn(token).updateCoupon(coupon));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping(path = "/coupon/delete")
	public ResponseEntity<?> deleteCoupon(@RequestHeader String token, @RequestParam Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCompanyLoggedIn(token).deleteCoupon(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/company/all-coupons")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCompanyLoggedIn(token).getCompanyCoupons());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/company/single-coupon")
	public ResponseEntity<?> getOneCoupon(@RequestHeader String token, @RequestParam Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCompanyLoggedIn(token).getOneCoupon(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/company/all-coupons-by-category")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token, @RequestParam Category category) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCompanyLoggedIn(token).getCompanyCoupons(category));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/company/all-coupons-by-MAX-price")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token, @RequestParam double maxPrice) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCompanyLoggedIn(token).getCompanyCoupons(maxPrice));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/logged-in-company/details")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader String token) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isCompanyLoggedIn(token).getCompanyDetails());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
