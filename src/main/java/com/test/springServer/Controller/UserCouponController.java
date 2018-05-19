package com.test.springServer.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.websocket.server.PathParam;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.springServer.mappers.UserCouponMapper;
import com.test.springServer.model.UserCoupon;
import com.test.springServer.service.UserCouponService;

@Controller
@ResponseBody
@CrossOrigin
//CORS(Cross-Origin Resource Sharing)라고 하여 도메인이 다른 서버에 대한 호출
public class UserCouponController {
	
	private final SqlSessionFactory sessionFactory;

	public UserCouponController(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private static Logger logger = LoggerFactory.getLogger(UserCouponController.class);

	@Autowired
	UserCouponService userCouponService;
	
	
	@GetMapping("/list")
	public List<UserCoupon> couponList() {
		
		List<UserCoupon> result = null;
		try (SqlSession session = sessionFactory.openSession()){
			UserCouponMapper mapper = session.getMapper(UserCouponMapper.class);
			System.out.println("모든 리스트");
			result = mapper.couponList();
			if(result != null){
				session.commit();
				session.close();
			}else{
				session.rollback();
				session.close();
			}
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	@GetMapping("/insert")
	public  Map<String, Object>  couponInsert(UserCoupon userCoupon) throws Exception{
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result = 0;
		try (SqlSession session = sessionFactory.openSession()){
			
			UserCouponMapper mapper = session.getMapper(UserCouponMapper.class);
			
			String couponCode = mapper.couponCode(userCoupon);
			
			if(couponCode == null) {
				//couponInsert(userCoupon);
				
				String coupon = couponnum();
				userCoupon.setCp_code(coupon);
				int cheak = couponCheak(userCoupon);
				boolean cheak_ok = false;
				if(cheak > 0) {
					int i = 1;
					do {
						coupon = couponnum();
						userCoupon.setCp_code(coupon);
						i = couponCheak(userCoupon);
					} while(i < 1);
					cheak_ok = true;
				}else {
					cheak_ok = true;
				}
				if(cheak_ok) {
					result = mapper.insert(userCoupon);
				}
				
				if(result > 0){
					session.commit();
					session.close();
				}else{
					session.rollback();
					session.close();
				}
				
				couponCode = coupon;
				resultMap.put("cp_code", couponCode);
				resultMap.put("gb", "insert");
				
			}else {
				resultMap.put("cp_code", couponCode);
				resultMap.put("gb", "state");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultMap;
	}
	
	//중복쿠폰코드 검사
	public int couponCheak(UserCoupon userCoupon) {
		int result = 0;
		
		try (SqlSession session = sessionFactory.openSession()){
			UserCouponMapper mapper = session.getMapper(UserCouponMapper.class);
			System.out.println("쿠폰");
			result = mapper.couponC(userCoupon);
			if(result > 0){
				session.commit();
				session.close();
			}else{
				session.rollback();
				session.close();
			}
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	//쿠폰생성
	public String couponnum(){
		String result = "";
		int couponSize = 1;

		final char[] Chars =
			{'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	  
		final int charCount = Chars.length;
		String[] arr = new String[couponSize];
		Random rnd = new Random();
		int index = 0;
		int i = 0;
		while (index < couponSize) {
			StringBuffer buf = new StringBuffer();
			for (i= 12; i > 0; i--) {
				buf.append(Chars[rnd.nextInt(charCount)]);
			}
			result = buf.toString();
			System.out.println("couponnum==>"+result);
			arr[index] = result;
			index++;
		}
		return result;
	}



	
	
	
}
