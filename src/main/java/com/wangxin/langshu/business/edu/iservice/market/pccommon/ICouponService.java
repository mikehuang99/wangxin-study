package com.wangxin.langshu.business.edu.iservice.market.pccommon;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangxin.langshu.business.edu.entity.market.Coupon;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponDeleteVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponQ;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponSaveVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponUpdateVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponViewVo;
import com.wangxin.langshu.business.edu.vo.market.coupon.CouponVo;
import com.wangxin.langshu.business.util.base.Page;

public interface ICouponService  extends IService<Coupon> {
	
	

	public Page<CouponVo> queryCoupons(CouponQ q) ;

	public boolean saveCoupon(CouponSaveVo vo) ;

	public boolean updateCoupon(CouponUpdateVo vo) ;

	
	public boolean deleteCoupon(CouponDeleteVo vo) ;

	
	public CouponViewVo viewCoupon(CouponViewVo vo) ;
	
	
	
	
}
