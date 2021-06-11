package com.wangxin.langshu.business.mobile.controller.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wangxin.langshu.business.edu.iservice.market.pcweb.IPcWebCouponService;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponQ;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponVo;
import com.wangxin.langshu.business.util.base.BaseController;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 优惠券管理
 *
 */
@RestController
@RequestMapping(value = "/mobileApi/market/coupon")
public class MobileApiCouponController extends BaseController {

	@Autowired
	private IPcWebCouponService pcWebCouponService;
	
	

	/**
	 * 查询所有可以领取的优惠券
	 * 
	 * @param CouponQ
	 * @return
	 */
	@ApiOperation(value = "查询所有可以领取的优惠券", notes = "查询所有可以领取的优惠券")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CouponVo>> list(@RequestBody CouponQ q) {	
		return pcWebCouponService.queryCoupons(q);
	}


	
	
	
	
	

}
