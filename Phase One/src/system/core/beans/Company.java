package system.core.beans;

import java.util.ArrayList;

public class Company {

	// Parameters
	private int compId;
	private String compName;
	private String compEmail;
	private String compPass;
	private ArrayList<Coupon> compCoupons = new ArrayList<>();

	// CTORS
	public Company(int compId, String compName, String compEmail, String compPass) {
		super();
		this.compId = compId;
		this.compName = compName;
		this.compEmail = compEmail;
		this.compPass = compPass;
	}

	public Company() {

	}

	// Getters & Setters
	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompEmail() {
		return compEmail;
	}

	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}

	public String getCompPass() {
		return compPass;
	}

	public void setCompPass(String compPass) {
		this.compPass = compPass;
	}

	public ArrayList<Coupon> getCompCoupons() {
		return compCoupons;
	}

	public void setCompCoupons(ArrayList<Coupon> compCoupons) {
		this.compCoupons = compCoupons;
	}

	// toString()
	@Override
	public String toString() {
		return "Company [ID=" + compId + ", Name=" + compName + ", Email=" + compEmail + ", Password=" + compPass + "]";
	}

}
