package system.core.beans;

public class CouponWithCustId {

	// Parameters
	private int custId;
	private int coupId;

	// CTOR
	public CouponWithCustId() {

	}

	public CouponWithCustId(int custId, int coupId) {
		super();
		this.custId = custId;
		this.coupId = coupId;
	}

	// Getter & Setters
	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getCoupId() {
		return coupId;
	}

	public void setCoupId(int coupId) {
		this.coupId = coupId;
	}

	// toString()
	@Override
	public String toString() {
		return "CouponWithCustId [custId=" + custId + ", coupId=" + coupId + "]";
	}

}
