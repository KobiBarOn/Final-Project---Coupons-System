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
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.session.Session;
import app.core.session.SessionContext;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/admin")
public class AdminController {

	@Autowired
	private SessionContext sessionContext;

	public AdminController() {

	}

	private AdminService isAdminLoggedIn(String token) {
		try {
			Session session = this.sessionContext.getSession(token);
			if (session != null) {
				AdminService adminService = (AdminService) session.getAttribute("AdminService");
				return adminService;
			}
			throw new CouponSystemException("There is no Session. You are NOT Logged-in!");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					"You must be Logged-in as ''ADMINISTRATOR'' in order to be able to use Administrative API."
							+ "You need to recieve a Token in order to use this API Controller");
		}
	}

	@PostMapping(path = "/company/add")
	public ResponseEntity<?> addCompany(@RequestHeader String token, @RequestBody Company company) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).addCompany(company));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping(path = "/company/update")
	public ResponseEntity<?> updateCompany(@RequestHeader String token, @RequestBody Company company) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).updateCompany(company));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping(path = "/company/delete")
	public ResponseEntity<?> deleteCompany(@RequestHeader String token, @RequestParam Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).deleteCompany(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/company/all-companies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader String token) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).getAllCompanies());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/company/single-company")
	public ResponseEntity<?> getOneCompany(@RequestHeader String token, @RequestParam Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).getOneCompany(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping(path = "/customer/add")
	public ResponseEntity<?> addCustomer(@RequestHeader String token, @RequestBody Customer customer) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).addCustomer(customer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping(path = "/customer/update")
	public ResponseEntity<?> updateCustomer(@RequestHeader String token, @RequestBody Customer customer) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).updateCustomer(customer));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping(path = "/customer/delete")
	public ResponseEntity<?> deleteCustomer(@RequestHeader String token, @RequestParam Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).deleteCustomer(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/customer/all-customers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader String token) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).getAllCustomers());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/customer/single-customer")
	public ResponseEntity<?> getOneCustomer(@RequestHeader String token, @RequestParam Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(isAdminLoggedIn(token).getOneCustomer(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
