package system.core.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import system.core.beans.Customer;
import system.core.connection.ConnectionPool;
import system.core.exceptions.CouponSystemException;

public class CustomersDBDAO implements CustomersDAO {

	/**
	 * Creates a Customer object and insert it (with his values) into 'customers'
	 * table in: 'coupons_project_db' Database.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void addCustomer(Customer customer) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "insert into customers values(0, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			if (customer != null) {
				pstmt.setString(1, customer.getFirstName());
				pstmt.setString(2, customer.getLastName());
				pstmt.setString(3, customer.getCustEmail());
				pstmt.setString(4, customer.getCustPass());
				pstmt.executeUpdate();

				System.out.println("New customer was added");
			}
		} catch (SQLException e) {
			throw new CouponSystemException("addCustomer Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Seeks for a Customer object in: 'customers' table in 'coupon_project_db'
	 * Database.
	 * 
	 * @param
	 * @return boolean about the query of the specific Customer by the given
	 *         parameters
	 * @throws CouponSystemException
	 */
	@Override
	public boolean isCustomerExists(String custEmail, String custPass) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from customers where customerEmail=? and customerPass=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, custEmail);
			pstmt.setString(2, custPass);
			ResultSet rs = pstmt.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			throw new CouponSystemException("isCustomerExists Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * An access method to update values (e.g, custEmail) of a specific Customer
	 * object.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "update customers set FirstName=?, LastName=?, customerEmail=?, customerPass=?"
					+ "where customerId=" + customer.getCustId();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getCustEmail());
			pstmt.setString(4, customer.getCustPass());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("updateCustomer Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Deletes a Customer object by the given custId parameter.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void deleteCustomer(int custId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from customers where customerId=" + custId;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Customer: " + custId + " is deleted");

		} catch (SQLException e) {
			throw new CouponSystemException("deleteCustomer Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Gets the entire 'customers' table from: 'coupons_project_db' Database.
	 * 
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	@Override
	public ArrayList<Customer> getAllCustomers() throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			ArrayList<Customer> allCustomers = new ArrayList<>();
			String sql = "select * from customers";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Customer cust = new Customer(rs.getInt("customerId"), rs.getString("FirstName"),
						rs.getString("LastName"), rs.getString("customerEmail"), rs.getString("customerPass"));
				allCustomers.add(cust);
			}

			return allCustomers;

		} catch (SQLException e) {
			throw new CouponSystemException("getAllCustomer Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Receives a custId parameters and Seeks for a Customer object by it, in:
	 * 'customers' table, in: 'coupons_project_db' Database.
	 * 
	 * @param
	 * @return Customer
	 * @throws CouponSystemException
	 */
	@Override
	public Customer getOneCustomerById(int custId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from customers where customerId=" + custId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Customer cust = new Customer();
				cust.setCustId(custId);
				cust.setFirstName(rs.getString("FirstName"));
				cust.setLastName(rs.getString("LastName"));
				cust.setCustEmail(rs.getString("customerEmail"));
				cust.setCustPass(rs.getString("customerPass"));

				return cust;
			} else {
				System.out.println("Couldn't find any customer by this ID: " + custId);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("getOneCustomerById Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
		return null;
	}

	/**
	 * Receives a custId parameters and Seeks for a Customer object by it, in:
	 * 'customers' table, in: 'coupons_project_db' Database.
	 * 
	 * @param
	 * @return Customer
	 * @throws CouponSystemException
	 */
	@Override
	public Customer getOneCustomerByEmail(String custEmail) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from customers where customerEmail='" + custEmail + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Customer cust = new Customer();
				cust.setCustId(rs.getInt("customerId"));
				cust.setFirstName(rs.getString("FirstName"));
				cust.setLastName(rs.getString("LastName"));
				cust.setCustEmail(custEmail);
				cust.setCustPass(rs.getString("customerPass"));

				return cust;
			} else {
				System.out.println("Couldn't find any customer by this Email address: " + custEmail);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("getOneCustomerByEmail Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
		return null;
	}

}
