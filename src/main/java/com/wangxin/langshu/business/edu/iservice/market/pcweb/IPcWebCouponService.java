package com.wangxin.langshu.business.edu.iservice.market.pcweb;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.Coupon;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponQ;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponVo;
import com.wangxin.langshu.business.util.base.Page;
import com.wangxin.langshu.business.util.base.Result;

public interface IPcWebCouponService  extends IService<Coupon> {
	
	
	/**
	 * 查询可以领取的优惠券
	 * @return
	 */
	public Result<Page<CouponVo>> queryCoupons(CouponQ q) ;

	
	
	
	
	
}
