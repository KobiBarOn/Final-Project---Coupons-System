package system.core.clients;

import system.core.daos.CompaniesDAO;
import system.core.daos.CompaniesDBDAO;
import system.core.daos.CouponsDAO;
import system.core.daos.CouponsDBDAO;
import system.core.daos.CustomersDAO;
import system.core.daos.CustomersDBDAO;
import system.core.exceptions.CouponSystemException;

public abstract class ClientFacade {

	protected CompaniesDAO companiesDAO = new CompaniesDBDAO();
	protected CustomersDAO customersDAO = new CustomersDBDAO();
	protected CouponsDAO couponsDAO = new CouponsDBDAO();

	public boolean login(String email, String password) throws CouponSystemException {
		return false;
	}

}
