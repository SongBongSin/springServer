package com.test.springServer.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.test.springServer.model.UserCoupon;
@Mapper
public interface UserCouponMapper {
	
	@Select("select * from myapp_user_coupon")
	public List<UserCoupon> couponList();
	
	@Select("select count(cp_code) from myapp_user_coupon where cp_code = #{cp_code}")
	public int couponC(UserCoupon usercoupon);
	
	@Select("SELECT cp_code FROM myapp_user_coupon where phone_num = #{phone_num}")
	public String couponCode(UserCoupon usercoupon);
	
	@Insert("INSERT INTO myapp_user_coupon(phone_num , cp_code , in_ymdh) \r\n" + 
			"         SELECT * FROM (SELECT #{phone_num} , #{cp_code} , now()) AS tmp\r\n" + 
			"			WHERE NOT EXISTS (\r\n" + 
			"			    SELECT phone_num FROM myapp_user_coupon WHERE phone_num = #{phone_num}\r\n" + 
			"			) LIMIT 1;")
	public int insert(UserCoupon usercoupon);
	

}
