package system.core.daos;

import java.util.ArrayList;
import system.core.beans.Coupon;
import system.core.beans.CouponWithCustId;
import system.core.exceptions.CouponSystemException;

public interface CouponsDAO {

	void addCoupon(Coupon coupon) throws CouponSystemException;

	void updateCoupon(Coupon coupon) throws CouponSystemException;

	void deleteCouponByCoupId(int coupId) throws CouponSystemException;

	void deleteCouponByCompId(int compId) throws CouponSystemException;

	ArrayList<Coupon> getAllCoupons() throws CouponSystemException;

	Coupon getOneCoupon(int couponId) throws CouponSystemException;

	ArrayList<Coupon> getCouponsByCompId(int compId) throws CouponSystemException;

	ArrayList<Coupon> getCouponsByCustId(int custId) throws CouponSystemException;

	void addCouponPurchase(int custId, int couponId) throws CouponSystemException;

	void deleteCouponPurchase(int custId, int couponId) throws CouponSystemException;

	ArrayList<CouponWithCustId> getCouponCustIdByCompId(int compId) throws CouponSystemException;

}
