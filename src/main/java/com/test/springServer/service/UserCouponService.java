package com.test.springServer.service;

import java.util.List;

import com.test.springServer.model.UserCoupon;

public interface UserCouponService {
	
	public List<UserCoupon> couponList();

	public String couponCode(UserCoupon usercoupon);
	
	public int insert(UserCoupon usercoupon);
	
	public int couponC(UserCoupon usercoupon);
}
