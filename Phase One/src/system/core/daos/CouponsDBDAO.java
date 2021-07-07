package system.core.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import system.core.beans.Category;
import system.core.beans.Coupon;
import system.core.beans.CouponWithCustId;
import system.core.connection.ConnectionPool;
import system.core.exceptions.CouponSystemException;

public class CouponsDBDAO implements CouponsDAO {

	/**
	 * Creates a Coupon object and insert it (with his values) into 'coupons' table
	 * in: 'coupons_project_db' Data Base.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void addCoupon(Coupon coupon) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "insert into coupons values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			if (coupon != null) {
				pstmt.setInt(1, coupon.getCompId());
				pstmt.setInt(2, coupon.getCategory().getCatId());
				pstmt.setString(3, coupon.getTitle());
				pstmt.setString(4, coupon.getDescription());
				pstmt.setDate(5, (Date) coupon.getStartDate());
				pstmt.setDate(6, (Date) coupon.getEndDate());
				pstmt.setInt(7, coupon.getAmount());
				pstmt.setDouble(8, coupon.getPrice());
				pstmt.setString(9, coupon.getImage());
				pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			throw new CouponSystemException("addCoupon Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * An access method to update values (e.g, title) of a specific Coupon object.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "update coupons set catId=?, title=?, description=?, startDate=?, endDate=?,"
					+ " amount=?, price=?, image=? where couponId=" + coupon.getCoupId() + " and companyId="
					+ coupon.getCompId();
			PreparedStatement pstmt = con.prepareStatement(sql);
			if (coupon != null) {
				pstmt.setInt(1, coupon.getCategory().getCatId());
				pstmt.setString(2, coupon.getTitle());
				pstmt.setString(3, coupon.getDescription());
				pstmt.setDate(4, (Date) coupon.getStartDate());
				pstmt.setDate(5, (Date) coupon.getEndDate());
				pstmt.setInt(6, coupon.getAmount());
				pstmt.setDouble(7, coupon.getPrice());
				pstmt.setString(8, coupon.getImage());
				pstmt.executeUpdate();

			}
		} catch (SQLException e) {
			throw new CouponSystemException("updateCoupon Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}

	}

	/**
	 * Deletes a Coupon object by the given coupId parameter.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void deleteCouponByCoupId(int coupId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from coupons where couponId=" + coupId;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Coupon ID: " + coupId + " is deleted");

		} catch (SQLException e) {
			throw new CouponSystemException("deleteCouponByCoupId Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Deletes a Coupon object by the given compId parameter.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void deleteCouponByCompId(int compId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from coupons where companyId=" + compId;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("Company coupons have been deleted");

		} catch (SQLException e) {
			throw new CouponSystemException("deleteCouponByCompId Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Gets the entire 'coupons' table from: 'coupons_project_db' Data Base.
	 * 
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons() throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			ArrayList<Coupon> allCoupons = new ArrayList<>();
			String sql = "select * from coupons";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Coupon coup = new Coupon(rs.getInt("couponId"), rs.getInt("companyId"),
						Category.values()[rs.getInt("catId") - 1], rs.getString("title"), rs.getString("description"),
						rs.getDate("startDate"), rs.getDate("endDate"), rs.getInt("amount"), rs.getDouble("price"),
						rs.getString("image"));

				allCoupons.add(coup);
			}

			return allCoupons;

		} catch (SQLException e) {
			throw new CouponSystemException("getAllCoupons Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Receives a coupId parameters and Seeks for a Coupon object by it (in:
	 * 'coupons' table).
	 * 
	 * @param
	 * @return Coupon
	 * @throws CouponSystemException
	 */
	@Override
	public Coupon getOneCoupon(int coupId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from coupons where couponId=" + coupId;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Coupon coup = new Coupon();
				coup.setCoupId(coupId);
				coup.setCompId(rs.getInt("companyId"));
				coup.setCategory(Category.values()[rs.getInt("catId") - 1]);
				coup.setTitle(rs.getString("title"));
				coup.setDescription(rs.getString("description"));
				coup.setStartDate(rs.getDate("startDate"));
				coup.setEndDate(rs.getDate("endDate"));
				coup.setAmount(rs.getInt("amount"));
				coup.setPrice(rs.getDouble("price"));
				coup.setImage(rs.getString("image"));

				return coup;
			} else {
				System.out.println("Couldn't find any coupon by this coupon ID: " + coupId);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("getOneCoupon Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
		return null;
	}

	/**
	 * Receives a compId parameter and Seeks for a Coupon object/s by it (in:
	 * 'coupons' table).
	 * 
	 * @param
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	@Override
	public ArrayList<Coupon> getCouponsByCompId(int compId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from coupons where companyId=" + compId;
			ArrayList<Coupon> companyCoupons = new ArrayList<>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Coupon coup = new Coupon();
				coup.setCoupId(rs.getInt("couponId"));
				coup.setCompId(compId);
				coup.setCategory(Category.values()[rs.getInt("catId") - 1]);
				coup.setTitle(rs.getString("title"));
				coup.setDescription(rs.getString("description"));
				coup.setStartDate(rs.getDate("startDate"));
				coup.setEndDate(rs.getDate("endDate"));
				coup.setAmount(rs.getInt("amount"));
				coup.setPrice(rs.getDouble("price"));
				coup.setImage(rs.getString("image"));
				companyCoupons.add(coup);
			}

			return companyCoupons;

		} catch (SQLException e) {
			throw new CouponSystemException("getCouponByCompId Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Receives a compId parameter and Seeks by it for a CouponWithCustId object/s
	 * (This type of object contains custId and coupId parameters). The returned
	 * ArrayList is of Coupon object/s That were purchased by Customer object/s.
	 * 
	 * @param
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	@Override
	public ArrayList<CouponWithCustId> getCouponCustIdByCompId(int compId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select customers_vs_coupons.customerId, coupons.couponId from coupons join"
					+ " customers_vs_coupons on coupons.couponId = customers_vs_coupons.couponId where companyId="
					+ compId;
			ArrayList<CouponWithCustId> couponsWithCustId = new ArrayList<>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CouponWithCustId coup = new CouponWithCustId();
				coup.setCoupId(rs.getInt("couponId"));
				coup.setCustId(rs.getInt("customerId"));
				couponsWithCustId.add(coup);
			}
			return couponsWithCustId;

		} catch (SQLException e) {
			throw new CouponSystemException("getCouponByCompId Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Receives a compId parameters and Seeks for a Coupon object/s by it (in:
	 * 'coupons' table).
	 * 
	 * @param
	 * @return ArrayList
	 * @throws CouponSystemException
	 */
	@Override
	public ArrayList<Coupon> getCouponsByCustId(int custId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "select * from customers_vs_coupons inner join "
					+ "coupons on customers_vs_coupons.couponId=coupons.couponId and customerId=" + custId;
			ArrayList<Coupon> customerCoupons = new ArrayList<>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Coupon coup = new Coupon();
				coup.setCoupId(rs.getInt("couponId"));
				coup.setCompId(rs.getInt("companyId"));
				coup.setCategory(Category.values()[rs.getInt("catId") - 1]);
				coup.setTitle(rs.getString("title"));
				coup.setDescription(rs.getString("description"));
				coup.setStartDate(rs.getDate("startDate"));
				coup.setEndDate(rs.getDate("endDate"));
				coup.setAmount(rs.getInt("amount"));
				coup.setPrice(rs.getDouble("price"));
				coup.setImage(rs.getString("image"));
				customerCoupons.add(coup);
			}

			return customerCoupons;

		} catch (SQLException e) {
			throw new CouponSystemException("getCouponByCompId Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Adds a Coupon purchase to table: 'customers_vs_coupons'.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void addCouponPurchase(int custId, int coupId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "insert into customers_vs_coupons values(?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, custId);
			pstmt.setInt(2, coupId);
			pstmt.executeUpdate();

			System.out.println("Coupon ID: " + coupId + " was purchased by customer ID: " + custId);

		} catch (SQLException e) {
			throw new CouponSystemException("addCouponPurchase Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

	/**
	 * Deletes a Coupon purchase from table: 'customers_vs_coupons'.
	 * 
	 * @param
	 * @throws CouponSystemException
	 */
	@Override
	public void deleteCouponPurchase(int custId, int coupId) throws CouponSystemException {
		Connection con = null;
		try {
			con = ConnectionPool.getInstance().getConnection();
			String sql = "delete from customers_vs_coupons where customerId=? and couponId=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, custId);
			pstmt.setInt(2, coupId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("deleteCouponPurchase Failed", e);
		} finally {
			if (con != null) {
				ConnectionPool.getInstance().restoreConnection(con);
			}
		}
	}

}
