package system.core.beans;

import java.util.ArrayList;

public class Customer {

	// Parameters
	private int custId;
	private String firstName;
	private String lastName;
	private String custEmail;
	private String custPass;
	private ArrayList<Coupon> custCoupons = new ArrayList<>();

	// CTORS
	// first CTOR doesn't include the coupons arraylist!?!??!
	public Customer(int custId, String firstName, String lastName, String custEmail, String custPass) {
		super();
		this.custId = custId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.custEmail = custEmail;
		this.custPass = custPass;
	}

	public Customer() {

	}

	// Getters & Setters
	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustPass() {
		return custPass;
	}

	public void setCustPass(String custPass) {
		this.custPass = custPass;
	}

	public ArrayList<Coupon> getCustCoupons() {
		return custCoupons;
	}

	public void setCustCoupons(ArrayList<Coupon> custCoupons) {
		this.custCoupons = custCoupons;
	}

	// by custEmail
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custEmail == null) ? 0 : custEmail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (custEmail == null) {
			if (other.custEmail != null)
				return false;
		} else if (!custEmail.equals(other.custEmail))
			return false;
		return true;
	}

	// toString()
	@Override
	public String toString() {
		return "Customer [ID=" + custId + ", Firstname=" + firstName + ", Lastname=" + lastName + ", Email=" + custEmail
				+ ", Password=" + custPass + "]";
	}

}
