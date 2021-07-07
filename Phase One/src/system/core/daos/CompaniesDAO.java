package system.core.daos;

import java.util.ArrayList;
import system.core.beans.Company;
import system.core.exceptions.CouponSystemException;

public interface CompaniesDAO {

	void addCompany(Company company) throws CouponSystemException;

	boolean isCompanyExists(String compEmail, String compPass) throws CouponSystemException;

	boolean isCompanyExistsByNameOrEmail(String compEmail, String compName) throws CouponSystemException;

	void updateCompany(Company company) throws CouponSystemException;

	void deleteCompany(int compId) throws CouponSystemException;

	ArrayList<Company> getAllCompanies() throws CouponSystemException;

	Company getOneCompanyById(int compId) throws CouponSystemException;

	Company getOneCompanyByEmail(String compEmail) throws CouponSystemException;

}
