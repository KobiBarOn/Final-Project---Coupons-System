package system.core.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import system.core.beans.Company;
import system.core.connection.ConnectionPool;
import system.core.exceptions.CouponSystemException;

public class CompaniesDBDAO implements CompaniesDAO {

	/**
	 * Creates a Company object and insert it (with his values) into 'companies'
	 * table in: 'coupons_project_db' Database.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void addCompany(Company company) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "insert into companies values(0, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			if (company != null) {
				pstmt.setString(1, company.getCompName());
				pstmt.setString(2, company.getCompEmail());
				pstmt.setString(3, company.getCompPass());
				pstmt.executeUpdate();

				System.out.println("New company was added");
			}
		} catch (SQLException e) {
			throw new CouponSystemException("addCompany Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Seeks for a Company object in: 'companies' table in 'coupon_project_db'
	 * Database.
	 * 
	 * @param
	 * @return boolean about the query of the specific Company by the given
	 *         parameters
	 * @throws CouponSystemException
	 */
	@Override
	public boolean isCompanyExists(String compEmail, String compPass) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from companies where companyEmail=? and companyPass=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compEmail);
			pstmt.setString(2, compPass);
			ResultSet rs = pstmt.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			throw new CouponSystemException("isCompanyExists Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Seeks for a Company object in: 'companies' table in 'coupon_project_db'
	 * Database.
	 * 
	 * @param
	 * @return boolean about the query of the specific Company by the given
	 *         parameters
	 * @throws CouponSystemException
	 */
	@Override
	public boolean isCompanyExistsByNameOrEmail(String compEmail, String compName) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from companies where companyEmail=? or companyName=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, compEmail);
			pstmt.setString(2, compName);
			ResultSet rs = pstmt.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			throw new CouponSystemException("isCompanyExistsByName Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * An access method to update values (e.g, companyName) of a specific Company
	 * object.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void updateCompany(Company company) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "update companies set companyName=?, companyEmail=?, companyPass=? " + "where companyId="
					+ company.getCompId();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getCompEmail());
			pstmt.setString(3, company.getCompPass());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("updateCompany Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Deletes a Company object by the given compId parameter.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void deleteCompany(int compId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from companies where companyId=" + compId;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Company with ID: " + compId + " was deleted");

		} catch (SQLException e) {
			throw new CouponSystemException("deleteCompany Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Gets the entire 'companies' table from: 'coupons_project_db' Database.
	 * 
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	@Override
	public ArrayList<Company> getAllCompanies() throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			ArrayList<Company> allCompanies = new ArrayList<>();
			String sql = "select * from companies";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Company comp = new Company(rs.getInt("companyId"), rs.getString("companyName"),
						rs.getString("companyEmail"), rs.getString("companyPass"));
				allCompanies.add(comp);
			}
			return allCompanies;

		} catch (SQLException e) {
			throw new CouponSystemException("getAllCompanies Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Receives a compId parameter and Seeks for a Company object by it, in:
	 * 'companies' table in: 'coupons_project_db' Database.
	 * 
	 * @param
	 * @return Company
	 * @throws CouponSystemException
	 */
	@Override
	public Company getOneCompanyById(int compId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from companies where companyId=" + compId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Company comp = new Company();
				comp.setCompId(compId);
				comp.setCompName(rs.getString("companyName"));
				comp.setCompEmail(rs.getString("companyEmail"));
				comp.setCompPass(rs.getString("companyPass"));

				return comp;
			} else {
				System.out.println("Couldn't find any company by this ID: " + compId);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("getOneCompanyById Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
		return null;
	}

	/**
	 * Receives a compEmail parameter and Seeks for a Company object by it, in:
	 * 'companies' table in: 'coupons_project_db' Database.
	 * 
	 * @param
	 * @return Company
	 * @throws CouponSystemException
	 */
	@Override
	public Company getOneCompanyByEmail(String compEmail) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from companies where companyEmail='" + compEmail + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Company comp = new Company();
				comp.setCompId(rs.getInt("companyId"));
				comp.setCompName(rs.getString("companyName"));
				comp.setCompEmail(compEmail);
				comp.setCompPass(rs.getString("companyPass"));

				return comp;
			} else {
				System.out.println("Couldn't find any company by this Email address: " + compEmail);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("getOneCompanyByEmail Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
		return null;
	}

}
