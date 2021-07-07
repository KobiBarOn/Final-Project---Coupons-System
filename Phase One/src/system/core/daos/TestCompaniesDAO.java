package system.core.daos;

import system.core.beans.Company;
import system.core.exceptions.CouponSystemException;

public class TestCompaniesDAO {

	public static void main(String[] args) throws CouponSystemException {

		CompaniesDAO dao = new CompaniesDBDAO();

		// Test addCompany
//		Company c1 = new Company(10, "google", "support@google.com", "gogogogo");
//		dao.addCompany(c1);

		// Test isCompanyExists
//		System.out.println("===========");
//		dao.isCompanyExists("support@google.com", "gogogogo");
//		System.out.println("===========");

		// Test updateCompany
//		System.out.println("Please enter the Company ID that you want to update her details:");
//		Company c2 = new Company(105, "google", "support@google.com", "gogogogo");
//		dao.updateCompany(c2);

		// Test deleteCompany
//		dao.deleteCompany(9);

		// Test getAllCompanies
//		dao.getAllCompanies();

		// Test getOneCompanyById
//		dao.getOneCompanyById(11);

		// Test getOneCompanyByEmail
//		dao.getOneCompanyByEmail("support@google.com");

	}

}
