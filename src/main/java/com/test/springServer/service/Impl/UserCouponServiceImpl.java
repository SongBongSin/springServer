package com.test.springServer.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springServer.mappers.UserCouponMapper;
import com.test.springServer.model.UserCoupon;
import com.test.springServer.service.UserCouponService;

@Service
public class UserCouponServiceImpl implements UserCouponService{
	
	@Autowired
	UserCouponMapper userCouponMapper;
	
	@Override
	public List<UserCoupon> couponList(){
		return userCouponMapper.couponList();
	}
	
	@Override
	public int couponC(UserCoupon userCoupon) {
		return userCouponMapper.couponC(userCoupon);
	}
	
		
	@Override
	public String couponCode(UserCoupon usercoupon) {
		// TODO Auto-generated method stub
		return userCouponMapper.couponCode(usercoupon);
	}

	@Override
	public int insert(UserCoupon userCoupon) {
		return userCouponMapper.insert(userCoupon);
	}
	
}
