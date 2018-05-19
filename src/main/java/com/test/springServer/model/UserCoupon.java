package com.test.springServer.model;

public class UserCoupon {
	
	private String sno;
	private String phone_num;
	private String cp_code;
	private String in_ymdh;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getCp_code() {
		return cp_code;
	}
	public void setCp_code(String cp_code) {
		this.cp_code = cp_code;
	}
	public String getIn_ymdh() {
		return in_ymdh;
	}
	public void setIn_ymdh(String in_ymdh) {
		this.in_ymdh = in_ymdh;
	}
	@Override
	public String toString() {
		return "UserCoupon [sno=" + sno + ", phone_num=" + phone_num + ", cp_code=" + cp_code + ", in_ymdh=" + in_ymdh
				+ "]";
	}
	
	

}
